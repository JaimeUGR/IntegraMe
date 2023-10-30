package com.integrame.app.core.data.model.content

import com.integrame.app.core.data.local.entities.Student

enum class EContentInteractionMethods {
    Default,
    Sequential,
    TabbedNavigation,
    Narrated
}

data class ContentInteractionMethods(
    val hasDefaultInteraction: Boolean,
    val hasSequentialInteraction: Boolean,
    val hasTabbedNavigationInteraction: Boolean,
    val hasNarratedInteraction: Boolean
)

enum class EContentAdaptationFormats {
    Text,
    Image,
    Video,
    Audio
}

data class ContentAdaptationFormats(
    val hasTextContent: Boolean,
    val hasImageContent: Boolean,
    val hasVideoContent: Boolean,
    val hasAudioContent: Boolean
)

data class ContentProfile(
    val student: Student,
    val interactionMethods: ContentInteractionMethods,
    val contentFormats: ContentAdaptationFormats
)
