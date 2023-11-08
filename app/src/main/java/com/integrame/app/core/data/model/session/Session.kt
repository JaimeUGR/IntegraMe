package com.integrame.app.core.data.model.session

import kotlinx.serialization.Serializable

@Serializable
data class Session(
    val userId: Int,
    val token: String
)
