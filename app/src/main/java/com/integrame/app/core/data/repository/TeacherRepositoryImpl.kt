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
