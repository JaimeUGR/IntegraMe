package com.integrame.app.login.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface StudentPassword

@Serializable
@SerialName("TextPassword")
data class TextPassword(
    val password: String
) : StudentPassword

@Serializable
@SerialName("ImagePassword")
data class ImagePassword(
    val password: List<Int>
) : StudentPassword
