package com.integrame.app.login.data.model

import com.integrame.app.core.data.model.content.ImageContent
import com.integrame.app.core.data.network.NetworkImageContent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface AuthMethod

@SerialName("TextAuth")
object TextAuthMethod : AuthMethod

@Serializable
@SerialName("ImageAuth")
data class ImageAuthMethod(
    val steps: Int,
    val imageList: List<ImageContent>
) : AuthMethod
