package com.integrame.app.login.ui.viewmodel

sealed interface AuthProcessUIState {
    object Pending: AuthProcessUIState
    object Requesting: AuthProcessUIState
    object Authorized: AuthProcessUIState
    object Unauthorized: AuthProcessUIState
    data class Error(val error: String): AuthProcessUIState
}
