package com.integrame.app.core.domain.usecase

import com.integrame.app.core.data.model.session.Session
import com.integrame.app.core.domain.repository.SessionRepository
import com.integrame.app.core.util.Option

// TODO: Refactor -> Mover a un archivo propio
class NonAuthorizedException(override val message: String) : Exception() {}

abstract class AuthenticatedUseCase<T> (
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke() : Result<T> {
        val sessionOpt = sessionRepository.getSession()

        return try {
            val result = onInvoke(when (sessionOpt) {
                is Option.Some -> sessionOpt.value
                is Option.None -> throw NonAuthorizedException("La sesión no está definida")
            })

            if (result.isFailure)
                result.getOrThrow()

            result
        } catch (e: NonAuthorizedException) {
            sessionRepository.invalidateSession()
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    abstract suspend fun onInvoke(session: Session) : Result<T>
}
