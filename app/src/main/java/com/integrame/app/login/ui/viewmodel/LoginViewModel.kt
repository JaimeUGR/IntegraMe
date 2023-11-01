package com.integrame.app.login.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.integrame.app.login.data.model.TextPassword
import com.integrame.app.login.data.repository.LoginState
import com.integrame.app.login.domain.repository.AuthRepository
import com.integrame.app.login.ui.model.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel(
) {
    // TODO: utilizar en un if y un LaunchEffect para lanzar la redirección al dashboard cuando hay éxito
    // También para mostrar el error del login en caso de que falle
    // Alternativamente, se puede convertir a un MutableStateFlow y un StateFlow público y utilizarlo para eventos
    var loginUIState: LoginUiState by mutableStateOf(LoginUiState.Pending)
        private set

    fun tryLoginTeacher(user: String, password: String) {
        viewModelScope.launch {
            checkLogin(authRepository.loginTeacher(user, password))
        }
    }

    // TODO: password debe ser StudentPassword
    fun tryLoginStudent(userID: Int, password: String) {
        viewModelScope.launch {
            checkLogin(authRepository.loginStudent(userID, TextPassword(password)))
        }
    }

    private fun checkLogin(loginState: LoginState) {
        loginUIState = when (loginState) {
            is LoginState.Sucess -> LoginUiState.Success
            is LoginState.Failed -> LoginUiState.Failed(loginState.error)
        }
    }
}
