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

package com.integrame.app.login.domain.usecase

import com.integrame.app.core.domain.repository.StudentRepository
import com.integrame.app.core.util.RequestResult
import com.integrame.app.login.data.model.StudentAuthProfile
import com.integrame.app.login.data.repository.IdentityCardRepositoryImpl
import javax.inject.Inject

class GetStudentAuthProfileUseCase @Inject constructor(
    private val studentRepository: StudentRepository,
    private val identityCardRepository: IdentityCardRepositoryImpl
) {
    suspend operator fun invoke(userId: Int) : RequestResult<StudentAuthProfile> {
        val requestIdentityCard = identityCardRepository.getIdentityCard(userId)
        val requestContentProfile = studentRepository.getContentProfile(userId)
        val requestAuthMethod = studentRepository.getAuthMethod(userId)

        return when {
            requestIdentityCard is RequestResult.Error -> RequestResult.Error(requestIdentityCard.error)
            requestContentProfile is RequestResult.Error -> RequestResult.Error(requestContentProfile.error)
            requestAuthMethod is RequestResult.Error -> RequestResult.Error(requestAuthMethod.error)
            else -> {
                RequestResult.Success(
                    StudentAuthProfile(
                        identityCard = (requestIdentityCard as RequestResult.Success).data,
                        contentProfile = (requestContentProfile as RequestResult.Success).data,
                        authMethod = (requestAuthMethod as RequestResult.Success).data
                    )
                )
            }
        }
    }
}
