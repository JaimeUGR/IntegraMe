package com.integrame.app.login.domain.usecase

import com.integrame.app.core.domain.repository.StudentRepository
import com.integrame.app.core.util.MyResult
import com.integrame.app.login.data.model.StudentAuthProfile
import com.integrame.app.login.data.repository.IdentityCardRepositoryImpl
import javax.inject.Inject

class GetStudentAuthProfileUseCase @Inject constructor(
    private val studentRepository: StudentRepository,
    private val identityCardRepository: IdentityCardRepositoryImpl
) {
    suspend operator fun invoke(userId: Int) : MyResult<StudentAuthProfile, GetAuthProfileError> {
        val identityCard = identityCardRepository.getIdentityCard(userId)
        val contentProfile = studentRepository.getContentProfile(userId)
        val authMethod = studentRepository.getAuthMethod(userId)

        return MyResult.Success(
            StudentAuthProfile(
                identityCard = identityCard.unwrap(),
                contentProfile = contentProfile.unwrap(),
                authMethod = authMethod.unwrap()
            )
        )
    }

    enum class GetAuthProfileError {
        ApiError,
        UnknownError
    }
}
