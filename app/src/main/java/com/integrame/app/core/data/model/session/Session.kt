package com.integrame.app.core.data.model.session

import com.integrame.app.core.data.local.entities.UserType
import kotlinx.serialization.Serializable

@Serializable
data class Session(
    val userId: Int,
    val token: String,
    val userType: UserType
)
