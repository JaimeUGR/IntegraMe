package com.integrame.app.core.data.repository

import com.integrame.app.core.data.model.content.ContentProfile
import com.integrame.app.core.data.network.IntegraMeApi
import com.integrame.app.core.data.network.toContentProfile
import com.integrame.app.core.domain.repository.StudentRepository
import com.integrame.app.core.util.MyResult
import com.integrame.app.login.data.model.AuthMethod
import javax.inject.Inject

class StudentRespositoryImpl @Inject constructor(
    private val api: IntegraMeApi
) : StudentRepository {
    override suspend fun getProfile(userId: Int): Int {
        TODO("Not yet implemented")
    }

    override suspend fun getContentProfile(userId: Int): MyResult<ContentProfile, Unit> {
        return MyResult.Success(api.getStudentContentProfile(userId).toContentProfile())
    }

    override suspend fun getAuthMethod(userId: Int): MyResult<AuthMethod, Unit> {
        return MyResult.Success(api.getStudentAuthMethod(userId).toAuthMethod())
    }
}