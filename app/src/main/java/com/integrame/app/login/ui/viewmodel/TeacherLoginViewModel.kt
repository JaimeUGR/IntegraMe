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

package com.integrame.app.login.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.integrame.app.core.util.AuthRequestResult
import com.integrame.app.login.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeacherLoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    var authProcessUIState: AuthProcessUIState by mutableStateOf(AuthProcessUIState.Pending)
        private set

    var nickname by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    fun onNicknameChange(newNickname: String) {
        nickname = newNickname
    }

    fun onPasswordChange(newPassword: String) {
        password = newPassword
    }

    fun resetAuthProcess() {
        if (authProcessUIState == AuthProcessUIState.Pending)
            return

        authProcessUIState = AuthProcessUIState.Pending
    }

    fun onSignIn() {
        viewModelScope.launch {
            if (authProcessUIState == AuthProcessUIState.Requesting)
                return@launch

            // Poner el estado a autenticando
            authProcessUIState = AuthProcessUIState.Requesting

            authProcessUIState = when (val requestResult = authRepository.signInTeacher(nickname, password)) {
                is AuthRequestResult.Authorized -> AuthProcessUIState.Authorized
                is AuthRequestResult.Unauthorized -> AuthProcessUIState.Unauthorized
                is AuthRequestResult.Error -> AuthProcessUIState.Error(requestResult.error)
            }
        }
    }
}
