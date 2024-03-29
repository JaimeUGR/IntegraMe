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
import com.integrame.app.core.util.RequestResult
import com.integrame.app.login.data.model.ImagePassword
import com.integrame.app.login.data.model.StudentAuthProfile
import com.integrame.app.login.data.model.StudentPassword
import com.integrame.app.login.data.model.TextPassword
import com.integrame.app.login.domain.repository.AuthRepository
import com.integrame.app.login.domain.usecase.GetStudentAuthProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class StudentAuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val getStudentAuthProfileUseCase: GetStudentAuthProfileUseCase
) : ViewModel() {
    var studentAuthUIState: StudentAuthUIState by mutableStateOf(StudentAuthUIState.Loading)
        private set

    var authProcessUIState: AuthProcessUIState by mutableStateOf(AuthProcessUIState.Pending)
        private set

    var textPassword by mutableStateOf("")
        private set

    // Par <Id Imagen, Índice en la lista de imágenes>
    var imagePassword by mutableStateOf(emptyList<Pair<Int, Int>>())
        private set

    fun loadStudentData(userId: Int) {
        viewModelScope.launch {
            studentAuthUIState = StudentAuthUIState.Loading
            studentAuthUIState = when (val requestResult = getStudentAuthProfileUseCase(userId)) {
                is RequestResult.Success -> StudentAuthUIState.UserLoaded(requestResult.data)
                is RequestResult.Error -> StudentAuthUIState.Error(requestResult.error)
            }
        }
    }

    fun onTextPasswordChange(newTextPassword: String) {
        textPassword = newTextPassword
    }

    fun onAddImage(sizeToSignIn: Int, userId: Int, imageId: Int, imageIndex: Int): Int {
        if (authProcessUIState == AuthProcessUIState.Requesting)
            return imagePassword.size

        imagePassword = imagePassword.toMutableList().apply {
            add(Pair(imageId, imageIndex))
        }

        if (imagePassword.size == sizeToSignIn) {
           imageAuthSignIn(userId)
           return imagePassword.size
        }

        // Poner el estado a autenticando
        authProcessUIState = AuthProcessUIState.Requesting

        val currentImagePassword = ImagePassword(imagePassword.map { id_idx -> id_idx.first })

        viewModelScope.launch {
            authProcessUIState = when (val requestResult = authRepository.checkImagePassword(userId, currentImagePassword)) {
                is AuthRequestResult.Authorized -> AuthProcessUIState.Pending
                is AuthRequestResult.Unauthorized -> AuthProcessUIState.Unauthorized
                is AuthRequestResult.Error -> AuthProcessUIState.Error(requestResult.error)
            }
        }

        return imagePassword.size
    }

    fun onRemoveImage() {
        imagePassword = imagePassword.dropLast(1)
    }

    fun resetAuthProcess() {
        if (authProcessUIState !is AuthProcessUIState.Pending)
        {
            authProcessUIState = AuthProcessUIState.Pending

            // Limpiar la contraseña de imágenes
            imagePassword = emptyList()
        }
    }

    fun textAuthSignIn(userId: Int) {
        viewModelScope.launch {
            signIn(userId, TextPassword(textPassword))
        }
    }

    fun imageAuthSignIn(userId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                signIn(userId, ImagePassword(imagePassword.map { id_idx ->
                    id_idx.first
                }))
            }
        }
    }

    private suspend fun signIn(userId: Int, password: StudentPassword) {
        if (authProcessUIState == AuthProcessUIState.Requesting)
            return

        // Poner el estado a autenticando
        authProcessUIState = AuthProcessUIState.Requesting

        authProcessUIState = when (val requestResult = authRepository.signInStudent(userId, password)) {
            is AuthRequestResult.Authorized -> AuthProcessUIState.Authorized
            is AuthRequestResult.Unauthorized -> AuthProcessUIState.Unauthorized
            is AuthRequestResult.Error -> AuthProcessUIState.Error(requestResult.error)
        }
    }
}

sealed interface StudentAuthUIState {
    object Loading : StudentAuthUIState
    data class UserLoaded(
        val studentAuthProfile: StudentAuthProfile
    ) : StudentAuthUIState
    data class Error(
        val error: String
    ) : StudentAuthUIState
}
