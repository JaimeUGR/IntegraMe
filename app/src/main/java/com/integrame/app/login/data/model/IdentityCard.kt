package com.integrame.app.login.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IdentityCard(
    val userID: Int,
    val name: String,
    val surnamesInitials: String,
    val avatarUrl: String
)
