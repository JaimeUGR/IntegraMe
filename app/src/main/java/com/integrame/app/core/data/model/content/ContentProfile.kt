package com.integrame.app.core.data.model.content

import com.integrame.app.core.data.local.entities.Student

enum class EContentInteractionMethods {
    Default,    // No es necesaria ninguna adaptación a la interacción
    Sequential,
    TabbedNavigation,
    Narrated,
    Simplified  // Discapacidad cognitiva
}

data class ContentInteractionMethods(
    val hasDefaultInteraction: Boolean,
    val hasSequentialInteraction: Boolean,
    val hasTabbedNavigationInteraction: Boolean,
    val hasNarratedInteraction: Boolean,
    val hasSimplifiedInteraction: Boolean
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
