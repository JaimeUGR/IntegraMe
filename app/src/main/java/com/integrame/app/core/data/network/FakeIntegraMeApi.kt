package com.integrame.app.core.data.network

import com.integrame.app.core.data.model.content.EContentAdaptationFormats
import com.integrame.app.core.data.model.content.EInteractionMethods
import com.integrame.app.core.data.model.session.Session
import com.integrame.app.login.data.model.ImagePassword
import com.integrame.app.login.data.model.TextPassword
import com.integrame.app.login.data.network.NetworkAuthMethod
import com.integrame.app.login.data.network.NetworkIdentityCard
import com.integrame.app.login.data.network.NetworkImageAuthMethod
import com.integrame.app.login.data.network.NetworkTextAuthMethod
import com.integrame.app.login.data.network.SignInStudentRequest
import com.integrame.app.login.data.network.SignInTeacherRequest
import kotlinx.coroutines.delay
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response

private object FakeResources {
    val networkImages: List<NetworkImageContent> = listOf(
        NetworkImageContent(0, "ASD"),
        NetworkImageContent(1, "BSD"),
        NetworkImageContent(2, "BSD"),
        NetworkImageContent(3, "BSD")
    )

    val contentProfiles = List(15) { i->
        NetworkContentProfile(
            contentAdaptationFormats = listOf(EContentAdaptationFormats.Image),
            interactionMethods = listOf(EInteractionMethods.Default)
        )
    }

    val identityCardList = List(15) { i -> NetworkIdentityCard(
        userId = i,
        nickname = "Nick Alumno Número $i",
        avatar = NetworkImageContent(
            id = i,
            altDescription = "Avatar Gallina"
        )
    )}

    val authMethodList = List(15) { i ->
        if (i%2 == 0)
            NetworkTextAuthMethod
        else
            NetworkImageAuthMethod(steps = 3, images = networkImages)
    }
}

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

    override suspend fun signInStudent(signInRequest: SignInStudentRequest): Session {
        delay(1500)

        if ( when (val password = signInRequest.password) {
            is TextPassword -> {
                password.password == "integrame"
            }
            is ImagePassword -> {
                password.password[0] == 0
            }
        })
            return Session(signInRequest.userId, "TOKEN")
        else
            throw HttpException(Response.error<Session>(401,   ResponseBody.create(
                "application/json".toMediaTypeOrNull(),
                "{\"error\":[\"Error de autenticación\"]}"
            )))
    }

    override suspend fun signInTeacher(signInRequest: SignInTeacherRequest): Session {
        delay(1000)
        TODO("Not yet implemented")
    }
}
