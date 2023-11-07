package com.integrame.app.login.data.model

import com.integrame.app.core.data.model.content.ImageContent
import com.integrame.app.core.data.network.NetworkImageContent
import kotlinx.serialization.Serializable

@Serializable
sealed interface AuthMethod

object TextAuthMethod : AuthMethod

data class ImageAuthMethod(
    val steps: Int,
    val imageList: List<ImageContent>
) : AuthMethod
