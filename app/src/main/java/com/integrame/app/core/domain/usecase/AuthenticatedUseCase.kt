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

package com.integrame.app.core.domain.usecase

import com.integrame.app.core.data.model.session.Session
import com.integrame.app.core.domain.repository.SessionRepository
import com.integrame.app.core.util.AuthRequestResult
import com.integrame.app.core.util.Option
import retrofit2.HttpException

/**
 * Clase wrapper para las peticiones de autenticación. Permite no tener que implementar
 * los catch de las excepciones por acceso a datos en cada repositorio.
 */
abstract class AuthenticatedUseCase<T> (
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke(): AuthRequestResult<T> {
        val sessionOpt = sessionRepository.getSession()

        return try {
            when (sessionOpt) {
                is Option.Some -> onInvoke(sessionOpt.value)
                is Option.None -> AuthRequestResult.Unauthorized()
            }
        } catch (e: HttpException) {
            val statusCode = e.code()

            return if (statusCode == 401)
                AuthRequestResult.Unauthorized()
            else
                AuthRequestResult.Error("Error code: $statusCode")
        } catch (e: Exception) {
            AuthRequestResult.Error("Error: ${e.message ?: " desconocido"}")
        }
    }

    abstract suspend fun onInvoke(session: Session): AuthRequestResult<T>
}
