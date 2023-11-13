package com.integrame.app.core.data.model.content

data class UserContentAdaptationFormats(
    val hasTextContent: Boolean,
    val hasImageContent: Boolean,
    val hasVideoContent: Boolean,
    val hasAudioContent: Boolean
) {
    companion object {
        fun fromEnumList(contentAdaptionFormatsList: List<ContentAdaptationFormats>): UserContentAdaptationFormats {
            var hasTextContent: Boolean = false
            var hasImageContent: Boolean = false
            var hasVideoContent: Boolean = false
            var hasAudioContent: Boolean = false

            for (interactionMethod in contentAdaptionFormatsList) {
                when (interactionMethod) {
                    ContentAdaptationFormats.Text -> hasTextContent = true
                    ContentAdaptationFormats.Image -> hasImageContent = true
                    ContentAdaptationFormats.Video -> hasVideoContent = true
                    ContentAdaptationFormats.Audio -> hasAudioContent = true
                }
            }

            return UserContentAdaptationFormats(
                hasTextContent, hasImageContent, hasVideoContent, hasAudioContent
            )
        }
    }
}
