package com.integrame.app.core.data.network.api

import com.integrame.app.core.data.fake.FakeResources
import com.integrame.app.core.data.model.content.ContentProfile
import com.integrame.app.core.data.model.session.Session
import com.integrame.app.core.data.model.user.StudentProfile
import com.integrame.app.core.data.network.NetworkSession
import com.integrame.app.core.data.network.toContentProfile
import com.integrame.app.login.data.model.AuthMethod
import com.integrame.app.login.data.model.IdentityCard
import com.integrame.app.login.data.model.ImagePassword
import com.integrame.app.login.data.model.TextPassword
import com.integrame.app.login.data.network.SignInStudentRequest
import com.integrame.app.login.data.network.SignInTeacherRequest
import com.integrame.app.login.data.network.toIdentityCard
import com.integrame.app.tasks.data.model.ClassroomMenu
import com.integrame.app.tasks.data.model.GenericTaskModel
import com.integrame.app.tasks.data.model.GenericTaskStep
import com.integrame.app.tasks.data.model.MaterialRequest
import com.integrame.app.tasks.data.model.MaterialTaskModel
import com.integrame.app.tasks.data.model.MenuTaskModel
import com.integrame.app.tasks.data.model.TaskCard
import com.integrame.app.tasks.data.model.TaskModel
import com.integrame.app.tasks.data.model.TaskType
import com.integrame.app.tasks.data.network.NetworkPostGenericTaskStepState
import com.integrame.app.tasks.data.network.NetworkPostMaterialRequestDelivered
import com.integrame.app.tasks.data.network.NetworkPostMenuOptionAmount
import com.integrame.app.teacher.data.model.task.TaskInfo
import kotlinx.coroutines.delay
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response

object FakeIntegraMeApi : IntegraMeApi {
    override suspend fun getStudentsIdentityCards(): List<IdentityCard> {
        delay(500)
        return FakeResources.identityCardList.map { it.toIdentityCard() }
    }

    override suspend fun getStudentIdentityCard(userId: Int): IdentityCard {
        delay(500)
        return FakeResources.identityCardList[userId].toIdentityCard()
    }

    override suspend fun getStudentContentProfile(userId: Int): ContentProfile {
        delay(500)
        return FakeResources.contentProfiles[userId].toContentProfile()
    }

    override suspend fun getStudentAuthMethod(userId: Int): AuthMethod {
        delay(500)
        return FakeResources.authMethodList[userId]
    }

    override suspend fun signInStudent(signInRequest: SignInStudentRequest): NetworkSession {
        delay(500)

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
        delay(500)

        if (signInRequest.nickname == "francx11" && signInRequest.password == "integrame")
            return NetworkSession(99, "TOKEN")
        else
            throw HttpException(Response.error<Session>(401,   ResponseBody.create(
                "application/json".toMediaTypeOrNull(),
                "{\"error\":[\"Error de autenticación\"]}"
            )))
    }

    override suspend fun getStudentProfile(userId: Int): StudentProfile {
        delay(500)
        return FakeResources.studentProfiles[0]
    }

    override suspend fun postTaskInfo(value: TaskInfo): TaskInfo {
        TODO("Not yet implemented")
    }

    override suspend fun getTaskCards(): List<TaskCard> {
        return FakeResources.taskCards
    }

    override suspend fun getTaskModel(taskId : Int): TaskModel {
        delay(500)
        return when (FakeResources.taskCards[taskId].taskType) {
            TaskType.GenericTask -> FakeResources.genericTaskModels[taskId]
            TaskType.MenuTask -> FakeResources.menuTaskModels[taskId]
            TaskType.MaterialTask -> FakeResources.materialTaskModels[taskId]
        }
    }

    override suspend fun getGenericTaskModel(taskId: Int): GenericTaskModel {
        delay(500)
        return FakeResources.genericTaskModels[taskId]
    }

    override suspend fun getGenericTaskStep(taskId: Int, stepId: Int): GenericTaskStep {
        delay(500)
        return FakeResources.genericTaskSteps[taskId][stepId]
    }

    override suspend fun toggleGenericTaskStepCompleted(
        taskId: Int,
        stepId: Int,
        stepState: NetworkPostGenericTaskStepState
    ) {
        delay(500)
        FakeResources.genericTaskSteps[taskId][stepId].isCompleted = stepState.isCompleted
    }

    override suspend fun getMenuTaskModel(taskId: Int): MenuTaskModel {
        delay(500)
        return FakeResources.menuTaskModels[taskId]
    }

    override suspend fun getMenuTaskClassroomMenus(taskId: Int): List<ClassroomMenu> {
        delay(500)
        return FakeResources.menuTaskClassroomMenus[taskId]
    }

    override suspend fun getMenuTaskClassroomMenu(taskId: Int, classroomId: Int): ClassroomMenu {
        delay(500)
        return FakeResources.menuTaskClassroomMenus[taskId][classroomId]
    }

    override suspend fun setClassroomMenuOptionAmount(
        taskId: Int,
        classroomId: Int,
        menuOptionId: Int,
        amount: NetworkPostMenuOptionAmount
    ) {
        FakeResources.menuTaskClassroomMenus[taskId][classroomId].menuOptions[menuOptionId].requestedAmount = amount.amount
    }

    override suspend fun getMaterialTaskModel(taskId: Int): MaterialTaskModel {
        delay(500)
        return FakeResources.materialTaskModels[taskId]
    }

    override suspend fun getMaterialTaskRequest(taskId: Int, requestId: Int): MaterialRequest {
        delay(500)
        return FakeResources.materialTaskRequests[taskId][requestId]
    }

    override suspend fun toggleMaterialRequestDelivered(
        taskId: Int,
        requestId: Int,
        isDelivered: NetworkPostMaterialRequestDelivered
    ) {
        delay(500)
        FakeResources.materialTaskRequests[taskId][requestId].isDelivered = isDelivered.isDelivered
    }
}
