package com.integrame.app.core.data.model.content

import android.graphics.Bitmap
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

enum class ContentType {
    TEXT,
    IMAGE,
    VIDEO,
    AUDIO
}

interface DynamicContent {
    val type: ContentType
}

data class TextContent(
    val text: String,
) : DynamicContent {
    override val type = ContentType.TEXT
}

/*data class ImageContent(
    val imageUrl: String,
    val altDescription: String,
    val id: Int
) : DynamicContent {
    override val type = ContentType.IMAGE
}*/

sealed class ImageContent : DynamicContent {
    override val type = ContentType.IMAGE
    abstract val id: Int
}

data class LocalImage(
    @DrawableRes override val id: Int,
    @StringRes val altDescription: Int
) : ImageContent()
data class BitMapImage(
    val bitmap: Bitmap,
    override val id: Int,
    val altDescription: String
) : ImageContent()
data class RemoteImage(
    val imageUrl: String,
    override val id: Int,
    val altDescription: String
) : ImageContent()

data class VideoContent(
    val videoUrl: String,
    val id: Int
) : DynamicContent {
    override val type = ContentType.VIDEO
}

data class AudioContent(
    val audioUrl: String,
    val id: Int
) : DynamicContent {
    override val type = ContentType.AUDIO
}
