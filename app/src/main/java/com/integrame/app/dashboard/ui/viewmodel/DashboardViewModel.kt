/*
 *
 * Copyright (c) IntegraMe - Champions 2024 - All rights reserved
 *
 *  You may use, distribute and modify this code under the terms of the
 *  Creative Commons Attribution-NonCommercial 4.0 International (CC BY-NC 4.0) license.
 *
 *  You should have received a copy of the CC BY-NC 4.0 license. Otherwise, read
 *  the full license text here: https://creativecommons.org/licenses/by-nc/4.0/legalcode
 *
 *  This file belongs to the project IntegraMe: https://github.com/JaimeUGR/IntegraMe
 */

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
        loadProfile()
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

    fun retryLoadProfile() {
        if (dashboardUIState is DashboardUIState.Error) {
            dashboardUIState = DashboardUIState.Loading
            loadProfile()
        }
    }

    private fun loadProfile() {
        viewModelScope.launch {
            dashboardUIState = when (val authRequestResult = getProfileUseCase()) {
                is AuthRequestResult.Authorized -> DashboardUIState.ProfileReady(authRequestResult.data)
                is AuthRequestResult.Unauthorized -> DashboardUIState.UnauthorizedError
                is AuthRequestResult.Error -> DashboardUIState.Error(authRequestResult.error)
            }
        }
    }
}

sealed interface DashboardUIState {
    object Loading: DashboardUIState
    data class ProfileReady(val profile: Profile): DashboardUIState
    object SignedOut: DashboardUIState
    object UnauthorizedError: DashboardUIState
    data class Error(val error: String): DashboardUIState
}
