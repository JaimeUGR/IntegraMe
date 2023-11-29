package com.integrame.app.core.data.model.content

import kotlinx.serialization.Serializable

@Serializable
data class ContentPack(
    val text: TextContent,
    val image: ImageContent,
    val video: VideoContent,
    val audio: AudioContent
) {
    fun getContent(contentType: ContentAdaptationFormats): DynamicContent = when (contentType) {
            ContentAdaptationFormats.Text -> text
            ContentAdaptationFormats.Image -> image
            ContentAdaptationFormats.Audio -> audio
            ContentAdaptationFormats.Video -> video
        }
}
