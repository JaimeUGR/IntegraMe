package com.integrame.app.login.ui.model

sealed interface LoginUiState {
    object Success : LoginUiState
    object Pending: LoginUiState
    data class Failed(val error: String) : LoginUiState
}