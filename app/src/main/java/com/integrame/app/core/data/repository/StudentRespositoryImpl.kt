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

package com.integrame.app.core.data.repository

import com.integrame.app.core.data.model.content.ContentProfile
import com.integrame.app.core.data.model.session.Session
import com.integrame.app.core.data.model.user.StudentProfile
import com.integrame.app.core.data.network.api.IntegraMeApi
import com.integrame.app.core.data.network.toContentProfile
import com.integrame.app.core.domain.repository.StudentRepository
import com.integrame.app.core.util.AuthRequestResult
import com.integrame.app.core.util.RequestResult
import com.integrame.app.login.data.model.AuthMethod
import javax.inject.Inject

class StudentRespositoryImpl @Inject constructor(
    private val api: IntegraMeApi
) : StudentRepository {
    override suspend fun getProfile(session: Session): AuthRequestResult<StudentProfile> {
        return AuthRequestResult.Authorized(api.getStudentProfile(session.userId))
    }

    override suspend fun getContentProfile(userId: Int): RequestResult<ContentProfile> {
        return RequestResult.Success(api.getStudentContentProfile(userId))
    }

    override suspend fun getAuthMethod(userId: Int): RequestResult<AuthMethod> {
        return RequestResult.Success(api.getStudentAuthMethod(userId))
    }
}
