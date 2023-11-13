package com.integrame.app.login.domain.usecase

import com.integrame.app.core.domain.repository.SessionRepository
import javax.inject.Inject

class SignOutUseCase @Inject constructor(
    private val sessionRepository: SessionRepository
) {
    suspend operator fun invoke() {
        sessionRepository.signOut()
    }
}
