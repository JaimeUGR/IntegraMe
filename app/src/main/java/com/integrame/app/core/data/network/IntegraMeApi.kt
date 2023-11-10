package com.integrame.app.core.data.network

import com.integrame.app.core.data.model.user.StudentProfile
import com.integrame.app.login.data.network.NetworkAuthMethod
import com.integrame.app.login.data.network.NetworkIdentityCard
import com.integrame.app.login.data.network.SignInStudentRequest
import com.integrame.app.login.data.network.SignInTeacherRequest
import retrofit2.http.Body
import retrofit2.http.GET
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

    // TODO: Pasar el token
    @GET("auth/students/{userId}/profile")
    suspend fun getStudentProfile(@Path("userId") userId: Int): StudentProfile

}

