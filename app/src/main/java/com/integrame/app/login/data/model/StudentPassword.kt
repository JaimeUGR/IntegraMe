package com.integrame.app.login.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class StudentPassword {
}

@Serializable
@SerialName("TextPassword")
class TextPassword(private val password: String) : StudentPassword() {
}

@Serializable
@SerialName("ImagePassword")
class ImagePassword(private val password: List<Int>) : StudentPassword()
