package com.integrame.app.core.data.model.content

import kotlinx.serialization.Serializable

data class ContentPack(
    val text: TextContent,
    val image: ImageContent,
    val video: VideoContent,
    val audio: AudioContent
)
