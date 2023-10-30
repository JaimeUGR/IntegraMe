package com.integrame.app.core.data.network

import com.integrame.app.login.data.network.NetworkIdentityCard
import retrofit2.http.GET

interface IntegraMeApi {
    // Poner los endpoints
    @GET("/login/student/identityCards")
    suspend fun getStudentsIdentityCards() : List<NetworkIdentityCard>
}

