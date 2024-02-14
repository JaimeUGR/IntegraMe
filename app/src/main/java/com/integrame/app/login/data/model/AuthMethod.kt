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

package com.integrame.app.login.data.model

import com.integrame.app.core.data.model.content.ImageContent
import com.integrame.app.core.data.network.NetworkImageContent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface AuthMethod

@Serializable
@SerialName("TextAuth")
object TextAuthMethod: AuthMethod

@Serializable
@SerialName("ImageAuth")
data class ImageAuthMethod(
    val steps: Int,
    val imageList: List<ImageContent>
): AuthMethod
