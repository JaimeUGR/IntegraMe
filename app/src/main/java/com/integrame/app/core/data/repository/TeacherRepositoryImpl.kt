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

import com.integrame.app.core.data.fake.FakeResources
import com.integrame.app.core.data.model.user.TeacherProfile
import com.integrame.app.core.domain.repository.TeacherRepository
import com.integrame.app.core.util.AuthRequestResult
import javax.inject.Inject

class TeacherRepositoryImpl @Inject constructor(

) : TeacherRepository {
    override suspend fun getProfile(userId: Int): AuthRequestResult<TeacherProfile> {
        // TODO: Cambiar por petición api
        return AuthRequestResult.Authorized(
            TeacherProfile(
                userId = userId,
                nickname = "",
                avatar = FakeResources.remoteImages[0]
            )
        )
    }
}
