/*
 *
 * Copyright (c) IntegraMe - Champions 2024 - All rights reserved
 *
 *  You may use, distribute and modify this code under the terms of the
 *  Creative Commons Attribution-NonCommercial 4.0 International (CC BY-NC 4.0) license.
 *
 *  You should have received a copy of the CC BY-NC 4.0 license. Otherwise, read
 *  the full license text here: https://creativecommons.org/licenses/by-nc/4.0/legalcode
 *
 *  This file belongs to the project IntegraMe: https://github.com/JaimeUGR/IntegraMe
 */

package com.integrame.app.login.data.network

import com.integrame.app.core.data.network.NetworkImageContent
import com.integrame.app.core.data.network.toImageContent
import com.integrame.app.login.data.model.AuthMethod
import com.integrame.app.login.data.model.ImageAuthMethod
import com.integrame.app.login.data.model.TextAuthMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface NetworkAuthMethod {
    fun toAuthMethod() : AuthMethod
}

@Serializable
@SerialName("TextAuth")
object NetworkTextAuthMethod : NetworkAuthMethod {
    override fun toAuthMethod(): AuthMethod {
        return TextAuthMethod
    }
}

@Serializable
@SerialName("ImageAuth")
data class NetworkImageAuthMethod(
    val steps: Int,
    val images: List<NetworkImageContent>
) : NetworkAuthMethod {
    override fun toAuthMethod(): AuthMethod {
        return ImageAuthMethod(steps, images.map { networkImageContent ->
            networkImageContent.toImageContent()
        })
    }
}
