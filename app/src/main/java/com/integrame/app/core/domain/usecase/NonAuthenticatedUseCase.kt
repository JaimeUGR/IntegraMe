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
