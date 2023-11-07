package com.integrame.app.core.data.model.content

import kotlinx.serialization.Serializable

@Serializable
enum class EInteractionMethods {
    Default,    // No es necesaria ninguna adaptación a la interacción
    Sequential,
    TabbedNavigation,
    Narrated,
    Simplified  // Discapacidad cognitiva
}

@Serializable
enum class EContentAdaptationFormats {
    Text,
    Image,
    Video,
    Audio
}

data class InteractionMethods(
    val hasDefaultInteraction: Boolean,
    val hasSequentialInteraction: Boolean,
    val hasTabbedNavigationInteraction: Boolean,
    val hasNarratedInteraction: Boolean,
    val hasSimplifiedInteraction: Boolean
) {
    companion object {
        fun fromEnumList(eInteractionMethodsList: List<EInteractionMethods>) : InteractionMethods {
            var hasDefaultInteraction: Boolean = false
            var hasSequentialInteraction: Boolean = false
            var hasTabbedNavigationInteraction: Boolean = false
            var hasNarratedInteraction: Boolean = false
            var hasSimplifiedInteraction: Boolean = false

            for (interactionMethod in eInteractionMethodsList) {
                when (interactionMethod) {
                    EInteractionMethods.Default -> hasDefaultInteraction = true
                    EInteractionMethods.Sequential -> hasSequentialInteraction = true
                    EInteractionMethods.TabbedNavigation -> hasTabbedNavigationInteraction = true
                    EInteractionMethods.Narrated -> hasNarratedInteraction = true
                    EInteractionMethods.Simplified -> hasSimplifiedInteraction = true
                }
            }

            return InteractionMethods(
                hasDefaultInteraction, hasSequentialInteraction, hasTabbedNavigationInteraction, hasNarratedInteraction, hasSimplifiedInteraction
            )
        }
    }
}

data class ContentAdaptationFormats(
    val hasTextContent: Boolean,
    val hasImageContent: Boolean,
    val hasVideoContent: Boolean,
    val hasAudioContent: Boolean
) {
    companion object {
        fun fromEnumList(eContentAdaptionFormatsList: List<EContentAdaptationFormats>) : ContentAdaptationFormats {
            var hasTextContent: Boolean = false
            var hasImageContent: Boolean = false
            var hasVideoContent: Boolean = false
            var hasAudioContent: Boolean = false

            for (interactionMethod in eContentAdaptionFormatsList) {
                when (interactionMethod) {
                    EContentAdaptationFormats.Text -> hasTextContent = true
                    EContentAdaptationFormats.Image -> hasImageContent = true
                    EContentAdaptationFormats.Video -> hasVideoContent = true
                    EContentAdaptationFormats.Audio -> hasAudioContent = true
                }
            }

            return ContentAdaptationFormats(
                hasTextContent, hasImageContent, hasVideoContent, hasAudioContent
            )
        }
    }
}

data class ContentProfile(
    val interactionMethods: InteractionMethods,
    val contentAdaptationFormats: ContentAdaptationFormats
)
