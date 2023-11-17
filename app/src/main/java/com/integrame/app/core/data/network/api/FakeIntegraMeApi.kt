package com.integrame.app.core.data.network.api

import com.integrame.app.core.data.fake.FakeResources
import com.integrame.app.core.data.model.content.ContentAdaptationFormats
import com.integrame.app.core.data.model.content.InteractionMethods
import com.integrame.app.core.data.model.session.Session
import com.integrame.app.core.data.model.user.StudentProfile
import com.integrame.app.core.data.network.NetworkContentProfile
import com.integrame.app.core.data.network.NetworkImageContent
import com.integrame.app.core.data.network.NetworkSession
import com.integrame.app.core.data.network.toContentProfile
import com.integrame.app.core.data.network.toImageContent
import com.integrame.app.login.data.model.ImagePassword
import com.integrame.app.login.data.model.TextPassword
import com.integrame.app.login.data.network.NetworkAuthMethod
import com.integrame.app.login.data.network.NetworkIdentityCard
import com.integrame.app.login.data.network.NetworkImageAuthMethod
import com.integrame.app.login.data.network.NetworkTextAuthMethod
import com.integrame.app.login.data.network.SignInStudentRequest
import com.integrame.app.login.data.network.SignInTeacherRequest
import com.integrame.app.tasks.data.model.MenuTask
import kotlinx.coroutines.delay
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.http.Path

object FakeIntegraMeApi : IntegraMeApi {
    override suspend fun getStudentsIdentityCards(): List<NetworkIdentityCard> {
        delay(1000)
        return FakeResources.identityCardList
    }

    override suspend fun getStudentIdentityCard(userId: Int): NetworkIdentityCard {
        delay(1000)
        return FakeResources.identityCardList[userId]
    }

    override suspend fun getStudentContentProfile(userId: Int): NetworkContentProfile {
        return FakeResources.contentProfiles[userId]
    }

    override suspend fun getStudentAuthMethod(userId: Int): NetworkAuthMethod {
        delay(1000)
        return FakeResources.authMethodList[userId]
    }

    override suspend fun signInStudent(signInRequest: SignInStudentRequest): NetworkSession {
        delay(1500)

        if ( when (val password = signInRequest.password) {
            is TextPassword -> {
                password.password == "integrame"
            }
            is ImagePassword -> {
                password.password[0] == 0
            }
        })
            return NetworkSession(signInRequest.userId, "TOKEN")
        else
            throw HttpException(Response.error<Session>(401,   ResponseBody.create(
                "application/json".toMediaTypeOrNull(),
                "{\"error\":[\"Error de autenticación\"]}"
            )))
    }

    override suspend fun signInTeacher(signInRequest: SignInTeacherRequest): NetworkSession {
        delay(1500)

        if (signInRequest.nickname == "francx11" && signInRequest.password == "integrame")
            return NetworkSession(99, "TOKEN")
        else
            throw HttpException(Response.error<Session>(401,   ResponseBody.create(
                "application/json".toMediaTypeOrNull(),
                "{\"error\":[\"Error de autenticación\"]}"
            )))
    }

    override suspend fun getStudentProfile(userId: Int): StudentProfile {
        delay(1000)
        return FakeResources.studentProfiles[0]
    }
    /*

    override fun getMenuTask(@Path("taskId") taskId : Int): MenuTask{
        delay(1000)
        return FakeResources.
    }

     */


}
