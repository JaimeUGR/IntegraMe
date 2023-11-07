package com.integrame.app.core.data.network

import com.integrame.app.login.data.network.NetworkAuthMethod
import com.integrame.app.login.data.network.NetworkIdentityCard
import retrofit2.http.GET
import retrofit2.http.Path

interface IntegraMeApi {
    // Poner los endpoints
    @GET("/students/identityCards")
    suspend fun getStudentsIdentityCards() : List<NetworkIdentityCard>

    @GET("/students/{userId}/identityCard")
    suspend fun getStudentIdentityCard(@Path("userId") userId: Int) : NetworkIdentityCard

    @GET("/students/{userId}/contentProfile")
    suspend fun getStudentContentProfile(@Path("userId") userId: Int) : NetworkContentProfile

    @GET("/students/{userId}/authMethod")
    suspend fun getStudentAuthMethod(@Path("userId") userId: Int) : NetworkAuthMethod
}

