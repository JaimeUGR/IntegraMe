package com.integrame.app.dashboard.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.integrame.app.core.data.model.user.Profile
import com.integrame.app.core.domain.usecase.GetProfileUseCase
import com.integrame.app.core.util.AuthRequestResult
import com.integrame.app.login.domain.usecase.SignOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val signOutUseCase: SignOutUseCase
) : ViewModel() {
    var dashboardUIState: DashboardUIState by mutableStateOf(DashboardUIState.Loading)
        private set

    init {
        viewModelScope.launch {
            when (val authRequestResult = getProfileUseCase()) {
                is AuthRequestResult.Authorized -> dashboardUIState = DashboardUIState.ProfileReady(authRequestResult.data)
                is AuthRequestResult.Unauthorized -> TODO("Mostrar botón error y redirección a login")
                is AuthRequestResult.Error -> TODO("Mostrar botón error y reintentar")
            }
        }
    }

    fun signOut() {
        // TODO: Tiene que lanzarse en background para que no esté bindeado al ciclo de vida
        // del viewmodel (por ejemplo, si el usuario cierra la app depsués de pulsar cerrar sesión)
        viewModelScope.launch {
            dashboardUIState = DashboardUIState.Loading
            signOutUseCase()
            dashboardUIState = DashboardUIState.SignedOut
        }
    }
}

// TODO: Se podría añadir un estado para cuadno se está cerrando sesión
sealed interface DashboardUIState {
    object Loading: DashboardUIState
    data class ProfileReady(val profile: Profile): DashboardUIState
    object SignedOut: DashboardUIState
}
