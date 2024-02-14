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

package com.integrame.app.core.data.network

import com.integrame.app.core.data.model.content.ImageContent
import com.integrame.app.core.data.model.content.RemoteImage
import kotlinx.serialization.Serializable

// TODO: Reemplazado por @Serializable ImageContent
@Serializable
class NetworkImageContent(
    val id: Int,
    val altDescription: String,
    val imageUrl: String? = null
)

fun NetworkImageContent.toImageContent() : ImageContent {
    return RemoteImage(
        imageUrl = imageUrl ?: "https://34.175.9.11:30000/api/v1/images/$id",
        id = id,
        altDescription = altDescription
    )
}
