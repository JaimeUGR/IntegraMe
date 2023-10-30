package com.integrame.app.core.data.model.content

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

data class ImageContent(
    val imgUrl: String,
    val altDescription: String,
    val id: Int
) : DynamicContent {
    override val type = ContentType.IMAGE
}

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
