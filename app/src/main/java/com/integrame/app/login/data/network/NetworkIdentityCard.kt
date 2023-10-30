package com.integrame.app.login.data.network

import com.integrame.app.login.data.model.IdentityCard
import kotlinx.serialization.Serializable

@Serializable
data class NetworkIdentityCard(
    val userID: Int,
    val name: String,
    val surnamesInitials: String,
    val avatarUrl: String
)

fun NetworkIdentityCard.asIdentityCard() = IdentityCard(
    userID = userID,
    name = name,
    surnamesInitials = surnamesInitials,
    avatarUrl = avatarUrl
)
