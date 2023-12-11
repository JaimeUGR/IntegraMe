package com.integrame.app.core.data.network.api

import com.integrame.app.core.data.model.user.StudentProfile
import com.integrame.app.core.data.network.NetworkContentProfile
import com.integrame.app.core.data.network.NetworkSession
import com.integrame.app.login.data.model.AuthMethod
import com.integrame.app.login.data.network.NetworkIdentityCard
import com.integrame.app.login.data.network.SignInStudentRequest
import com.integrame.app.login.data.network.SignInTeacherRequest
import com.integrame.app.tasks.data.model.GenericTaskModel
import com.integrame.app.tasks.data.model.GenericTaskStep
import com.integrame.app.tasks.data.model.MaterialRequest
import com.integrame.app.tasks.data.model.MaterialTaskModel
import com.integrame.app.tasks.data.model.MenuTask
import com.integrame.app.tasks.data.model.MenuTaskModel
import com.integrame.app.tasks.data.model.Task
import com.integrame.app.tasks.data.model.TaskCard
import com.integrame.app.teacher.data.model.task.TaskInfo
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface IntegraMeApi {
    @GET("students/identityCards")
    suspend fun getStudentsIdentityCards(): List<NetworkIdentityCard>

    @GET("students/{userId}/identityCard")
    suspend fun getStudentIdentityCard(@Path("userId") userId: Int): NetworkIdentityCard

    @GET("students/{userId}/contentProfile")
    suspend fun getStudentContentProfile(@Path("userId") userId: Int): NetworkContentProfile

    @GET("students/{userId}/authMethod")
    suspend fun getStudentAuthMethod(@Path("userId") userId: Int): AuthMethod

    @POST("students/login")
    suspend fun signInStudent(@Body signInRequest: SignInStudentRequest): NetworkSession

    @POST("teachers/signIn")
    suspend fun signInTeacher(@Body signInRequest: SignInTeacherRequest): NetworkSession

    @Headers("Authorized")
    @GET("auth/students/{userId}/profile")
    suspend fun getStudentProfile(@Path("userId") userId: Int): StudentProfile

    @Headers("Authorized")
    @GET("auth/students/tasks/{taskId}")
    suspend fun getMenuTask(@Path("taskId") taskId : Int): MenuTask

    @POST("tasks/update")
    suspend fun postTaskInfo(@Body taskInfo: TaskInfo): TaskInfo

    //
    // Tasks
    //
    @Headers("Authorized")
    @GET("auth/students/taskCards")
    suspend fun getTaskCards(): List<TaskCard>

    @Headers("Authorized")
    @GET("auth/students/tasks/{taskId}")
    suspend fun getTask(@Path("taskId") taskId : Int): Task

    //
    // GenericTask
    //
    @Headers("Authorized")
    @GET("auth/students/tasks/generic/{taskId}")
    suspend fun getGenericTaskModel(@Path("taskId") taskId: Int): GenericTaskModel

    @Headers("Authorized")
    @GET("auth/students/tasks/generic/{taskId}/{stepId}")
    suspend fun getGenericTaskStep(
        @Path("taskId") taskId: Int,
        @Path("stepId") stepId: Int
    ): GenericTaskStep

    @Headers("Authorized")
    @POST("auth/students/tasks/generic/{taskId}/{stepId}/completed")
    suspend fun toggleGenericTaskStepCompleted(
        @Path("taskId") taskId: Int,
        @Path("stepId") stepId: Int,
        @Body isCompleted: Boolean
    )

    //
    // Menu Task
    //
    @Headers("Authorized")
    @GET("auth/students/tasks/menu/{taskId}")
    suspend fun getMenuTaskModel(@Path("taskId") taskId: Int): MenuTaskModel
    // TODO: Continuar aquí abajo los del menú

    //
    // Material Task
    //
    @Headers("Authorized")
    @GET("auth/students/tasks/material/{taskId}")
    suspend fun getMaterialTaskModel(@Path("taskId") taskId: Int): MaterialTaskModel

    @Headers("Authorized")
    @GET("auth/students/tasks/material/{taskId}/{requestId}")
    suspend fun getMaterialTaskRequest(
        @Path("taskId") taskId: Int,
        @Path("requestId") requestId: Int
    ): MaterialRequest

    @Headers("Authorized")
    @POST("auth/students/tasks/material/{taskId}/{requestId}/delivered")
    suspend fun toggleMaterialRequestDelivered(
        @Path("taskId") taskId: Int,
        @Path("requestId") requestId: Int,
        @Body isDelivered: Boolean
    )
}
