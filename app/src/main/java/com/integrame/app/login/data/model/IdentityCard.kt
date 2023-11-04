package com.integrame.app.login.data.model

import com.integrame.app.core.data.model.content.ImageContent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// TODO: Soporte de @Serializable
data class IdentityCard(
    val userID: Int,
    val nickname: String,
    val avatar: ImageContent
)
