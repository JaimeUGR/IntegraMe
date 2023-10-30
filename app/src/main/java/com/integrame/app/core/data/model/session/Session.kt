package com.integrame.app.core.data.model.session

data class Session(
    val userID: Int,
    val token: String,
    val lastUsedAt: Long,
    val expiresAt: Long
)
