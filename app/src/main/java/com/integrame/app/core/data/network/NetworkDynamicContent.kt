package com.integrame.app.core.data.network

import com.integrame.app.core.data.model.content.ImageContent
import com.integrame.app.core.data.model.content.RemoteImage
import kotlinx.serialization.Serializable

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
