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

import com.integrame.app.core.data.local.entities.UserType
import com.integrame.app.core.data.model.session.Session
import com.integrame.app.core.data.model.user.Profile
import com.integrame.app.core.domain.repository.SessionRepository
import com.integrame.app.core.domain.repository.StudentRepository
import com.integrame.app.core.domain.repository.TeacherRepository
import com.integrame.app.core.util.AuthRequestResult
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(
    private val sessionRepository: SessionRepository,
    private val studentRepository: StudentRepository,
    private val teacherRepository: TeacherRepository
): AuthenticatedUseCase<Profile>(sessionRepository) {
    override suspend fun onInvoke(session: Session): AuthRequestResult<Profile> {
        return when (session.userType) {
            UserType.Student -> {
                studentRepository.getProfile(session)
            }
            UserType.Teacher -> {
                teacherRepository.getProfile(session.userId)
            }
        }
    }
}
