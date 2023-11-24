package com.integrame.app.core.data.network.api

import com.integrame.app.core.data.fake.FakeResources
import com.integrame.app.core.data.model.session.Session
import com.integrame.app.core.data.model.user.StudentProfile
import com.integrame.app.core.data.network.NetworkContentProfile
import com.integrame.app.core.data.network.NetworkSession
import com.integrame.app.login.data.model.AuthMethod
import com.integrame.app.login.data.model.ImagePassword
import com.integrame.app.login.data.model.TextPassword
import com.integrame.app.login.data.network.NetworkIdentityCard
import com.integrame.app.login.data.network.SignInStudentRequest
import com.integrame.app.login.data.network.SignInTeacherRequest
import com.integrame.app.tasks.data.model.MenuTask
import com.integrame.app.tasks.data.model.Task
import com.integrame.app.teacher.data.model.task.TaskInfo
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

    override suspend fun getStudentAuthMethod(userId: Int): AuthMethod {
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


    override suspend fun getMenuTask(taskId : Int): MenuTask{
        delay(1000)
        return FakeResources.menuTasks[taskId]
    }

    override suspend fun postTaskInfo(value: TaskInfo): TaskInfo {
        TODO("Not yet implemented")
    }
    override suspend fun getTask(taskId : Int): Task {
        delay(1000)
        return FakeResources.tasks[taskId]
    }
}
