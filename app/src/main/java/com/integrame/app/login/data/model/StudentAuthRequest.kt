package com.integrame.app.login.data.model

import kotlinx.serialization.Serializable

@Serializable
data class StudentAuthRequest(
    val userId: Int,
    val password: StudentPassword
)
