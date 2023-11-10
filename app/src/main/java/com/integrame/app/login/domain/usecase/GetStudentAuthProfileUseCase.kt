package com.integrame.app.login.domain.usecase

import com.integrame.app.core.domain.repository.StudentRepository
import com.integrame.app.core.util.MyResult
import com.integrame.app.core.util.RequestResult
import com.integrame.app.login.data.model.StudentAuthProfile
import com.integrame.app.login.data.repository.IdentityCardRepositoryImpl
import javax.inject.Inject

// TODO: Añadir caso de uso básico de procesamiento de peticiones (similar al autenticado)
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
                contentProfile = if (contentProfile is RequestResult.Success) contentProfile.data else throw Exception(),
                authMethod = if (authMethod is RequestResult.Success) authMethod.data else throw Exception()
            )
        )
    }

    enum class GetAuthProfileError {
        ApiError,
        UnknownError
    }
}
