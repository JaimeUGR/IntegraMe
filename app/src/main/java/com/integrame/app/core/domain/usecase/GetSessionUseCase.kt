package com.integrame.app.core.domain.usecase

import com.integrame.app.core.data.model.session.Session
import com.integrame.app.core.domain.repository.SessionRepository
import com.integrame.app.core.util.Option
import javax.inject.Inject

class GetSessionUseCase @Inject constructor(
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke() : Option<Session> {
        // Si hay sesión habilitada, devuelve el token
        return sessionRepository.getSession()
    }
}
