package com.integrame.app.core.data.network.api

import com.integrame.app.core.data.model.content.ContentProfile
import com.integrame.app.core.data.model.user.StudentProfile
import com.integrame.app.core.data.network.NetworkSession
import com.integrame.app.login.data.model.AuthMethod
import com.integrame.app.login.data.model.IdentityCard
import com.integrame.app.login.data.network.SignInStudentRequest
import com.integrame.app.login.data.network.SignInTeacherRequest
import com.integrame.app.tasks.data.model.ClassroomMenu
import com.integrame.app.tasks.data.model.GenericTaskModel
import com.integrame.app.tasks.data.model.GenericTaskStep
import com.integrame.app.tasks.data.model.MaterialRequest
import com.integrame.app.tasks.data.model.MaterialTaskModel
import com.integrame.app.tasks.data.model.MenuTaskModel
import com.integrame.app.tasks.data.model.TaskCard
import com.integrame.app.tasks.data.model.TaskModel
import com.integrame.app.tasks.data.network.NetworkPostGenericTaskStepState
import com.integrame.app.tasks.data.network.NetworkPostMaterialRequestDelivered
import com.integrame.app.tasks.data.network.NetworkPostMenuOptionAmount
import com.integrame.app.teacher.data.model.task.TaskInfo
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface IntegraMeApi {
    @GET("students/identityCards")
    suspend fun getStudentsIdentityCards(): List<IdentityCard>

    @GET("students/{userId}/identityCard")
    suspend fun getStudentIdentityCard(@Path("userId") userId: Int): IdentityCard

    @GET("students/{userId}/contentProfile")
    suspend fun getStudentContentProfile(@Path("userId") userId: Int): ContentProfile

    @GET("students/{userId}/authMethod")
    suspend fun getStudentAuthMethod(@Path("userId") userId: Int): AuthMethod

    @POST("students/signIn")
    suspend fun signInStudent(@Body signInRequest: SignInStudentRequest): NetworkSession

    @POST("teachers/signIn")
    suspend fun signInTeacher(@Body signInRequest: SignInTeacherRequest): NetworkSession

    @Headers("Authorized: true")
    @GET("auth/students/{userId}/profile")
    suspend fun getStudentProfile(@Path("userId") userId: Int): StudentProfile

    @POST("tasks/update")
    suspend fun postTaskInfo(@Body taskInfo: TaskInfo): TaskInfo

    //
    // Tasks
    //
    @Headers("Authorized: true")
    @GET("auth/students/taskCards")
    suspend fun getTaskCards(): List<TaskCard>

    @Headers("Authorized: true")
    @GET("auth/students/tasks/{taskId}")
    suspend fun getTaskModel(@Path("taskId") taskId: Int): TaskModel

    //
    // GenericTask
    //
    @Headers("Authorized: true")
    @GET("auth/students/tasks/generic/{taskId}")
    suspend fun getGenericTaskModel(@Path("taskId") taskId: Int): GenericTaskModel

    @Headers("Authorized: true")
    @GET("auth/students/tasks/generic/{taskId}/{stepId}")
    suspend fun getGenericTaskStep(
        @Path("taskId") taskId: Int,
        @Path("stepId") stepId: Int
    ): GenericTaskStep

    @Headers("Authorized: true")
    @POST("auth/students/tasks/generic/{taskId}/{stepId}/completed")
    suspend fun toggleGenericTaskStepCompleted(
        @Path("taskId") taskId: Int,
        @Path("stepId") stepId: Int,
        @Body stepState: NetworkPostGenericTaskStepState
    )

    //
    // Menu Task
    //
    @Headers("Authorized: true")
    @GET("auth/students/tasks/menu/{taskId}")
    suspend fun getMenuTaskModel(@Path("taskId") taskId: Int): MenuTaskModel

    @Headers("Authorized: true")
    @GET("auth/students/tasks/menu/{taskId}/classrooms/menus")
    suspend fun getMenuTaskClassroomMenus(@Path("taskId") taskId: Int): List<ClassroomMenu>

    @Headers("Authorized: true")
    @GET("auth/students/tasks/menu/{taskId}/{classroomId}/menu")
    suspend fun getMenuTaskClassroomMenu(
        @Path("taskId") taskId: Int,
        @Path("classroomId") classroomId: Int
    ): ClassroomMenu

    @Headers("Authorized: true")
    @POST("auth/students/tasks/menu/{taskId}/{classroomId}/{menuOptionId}")
    suspend fun setClassroomMenuOptionAmount(
        @Path("taskId") taskId: Int,
        @Path("classroomId") classroomId: Int,
        @Path("menuOptionId") menuOptionId: Int,
        @Body amount: NetworkPostMenuOptionAmount
    )

    //
    // Material Task
    //
    @Headers("Authorized: true")
    @GET("auth/students/tasks/material/{taskId}")
    suspend fun getMaterialTaskModel(@Path("taskId") taskId: Int): MaterialTaskModel

    @Headers("Authorized: true")
    @GET("auth/students/tasks/material/{taskId}/{requestId}")
    suspend fun getMaterialTaskRequest(
        @Path("taskId") taskId: Int,
        @Path("requestId") requestId: Int
    ): MaterialRequest

    @Headers("Authorized: true")
    @POST("auth/students/tasks/material/{taskId}/{requestId}/delivered")
    suspend fun toggleMaterialRequestDelivered(
        @Path("taskId") taskId: Int,
        @Path("requestId") requestId: Int,
        @Body isDelivered: NetworkPostMaterialRequestDelivered
    )
}
