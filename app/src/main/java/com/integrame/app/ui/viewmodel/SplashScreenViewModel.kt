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

package com.integrame.app.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.integrame.app.core.domain.repository.SessionRepository
import com.integrame.app.core.util.Option
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val sessionRepository: SessionRepository
): ViewModel() {

    suspend fun initialize() {
        //sessionRepository.loadSession()
    }

    suspend fun hasAuthorizedSession(): Boolean {
        // TODO: Comprobar si el token sigue siendo válido?
        // No se debería hacer, para permitir el uso offline

        return when (val session = sessionRepository.getSession()) {
            is Option.Some -> true
            is Option.None -> false
        }
    }
}