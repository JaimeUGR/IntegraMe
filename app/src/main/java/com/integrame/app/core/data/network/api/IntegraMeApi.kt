package com.integrame.app.core.data.network.api

import com.integrame.app.core.data.model.user.StudentProfile
import com.integrame.app.core.data.network.NetworkContentProfile
import com.integrame.app.core.data.network.NetworkSession
import com.integrame.app.login.data.network.NetworkAuthMethod
import com.integrame.app.login.data.network.NetworkIdentityCard
import com.integrame.app.login.data.network.SignInStudentRequest
import com.integrame.app.login.data.network.SignInTeacherRequest
import com.integrame.app.tasks.data.model.MenuTask
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
    suspend fun getStudentAuthMethod(@Path("userId") userId: Int): NetworkAuthMethod

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


}
