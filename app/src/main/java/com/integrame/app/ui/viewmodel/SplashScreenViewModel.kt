package com.integrame.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.integrame.app.core.domain.repository.SessionRepository
import com.integrame.app.core.util.Option
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val sessionRepository: SessionRepository
) : ViewModel() {

    suspend fun initialize() {
        //sessionRepository.loadSession()
    }

    suspend fun hasAuthorizedSession() : Boolean {
        // TODO: Comprobar si el token sigue siendo válido?
        // No se debería hacer, para permitir el uso offline

        return when (val session = sessionRepository.getSession()) {
            is Option.Some -> true
            is Option.None -> false
        }
    }
}