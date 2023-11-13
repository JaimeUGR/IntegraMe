package com.integrame.app.core.data.network

import com.integrame.app.core.data.local.entities.UserType
import com.integrame.app.core.data.model.session.Session
import kotlinx.serialization.Serializable

@Serializable
data class NetworkSession(
    val userId: Int,
    val token: String
)

fun NetworkSession.toSession(userType: UserType) : Session {
    return Session(
        userId = userId,
        token = token,
        userType = userType
    )
}
