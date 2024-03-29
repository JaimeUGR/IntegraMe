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

import com.integrame.app.core.util.Option
import com.integrame.app.core.util.RequestResult
import retrofit2.HttpException

/**
 * Clase wrapper para las peticiones de autenticación. Permite no tener que implementar
 * los catch de las excepciones por acceso a datos en cada repositorio.
 */
abstract class NonAuthenticatedUseCase<T> {
    suspend operator fun invoke(): RequestResult<T> {
        return try {
            onInvoke()
        } catch (e: HttpException) {
            val statusCode = e.code()
            RequestResult.Error("Error code: $statusCode")
        } catch (e: Exception) {
            RequestResult.Error("Error: ${e.message ?: " desconocido"}")
        }
    }

    abstract suspend fun onInvoke(): RequestResult<T>
}
