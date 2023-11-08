package com.integrame.app.core.data.model.content

import kotlinx.serialization.Serializable

/**
 * Representa los posibles métodos de interacción de los usuarios.
 *
 * Los métodos de interacción modifican la interfaz, integrando adaptaciones
 * específicas para el usuario correspondiente.
 */
@Serializable
enum class EInteractionMethods {
    /**
     * Indica que no es necesaria ninguna adaptación adicional.
     */
    Default,

    /**
     * Indica que el usuario necesita / realizará recorridos automáticos
     * secuenciales.
     */
    Sequential,

    /**
     * Indica la necesidad de soportar navegación a través de tabulaciones
     * (por ejemplo teclado).
     */
    TabbedNavigation,

    /**
     * Indica adaptaciones considerando que el usuario utiliza lectores de pantalla.
     */
    Narrated,

    /**
     * Indica adaptaciones considerando que el usuario utiliza control por voz.
     */
    VoiceControl,

    /**
     * Indica adaptaciones para simplificar el diseño de la interfaz.
     */
    Simplified
}

@Serializable
enum class EContentAdaptationFormats {
    /**
     * Contenido en formato textual.
     */
    Text,

    /**
     * Contenido en formato visual.
     */
    Image,

    /**
     * Contenido en formato audiovisual.
     */
    Video,

    /**
     * Contenido en formato auditivo.
     */
    Audio
}

data class InteractionMethods(
    val hasDefaultInteraction: Boolean,
    val hasSequentialInteraction: Boolean,
    val hasTabbedNavigationInteraction: Boolean,
    val hasNarratedInteraction: Boolean,
    val hasVoiceControlInteraction: Boolean,
    val hasSimplifiedInteraction: Boolean
) {
    companion object {
        fun fromEnumList(eInteractionMethodsList: List<EInteractionMethods>) : InteractionMethods {
            var hasDefaultInteraction: Boolean = false
            var hasSequentialInteraction: Boolean = false
            var hasTabbedNavigationInteraction: Boolean = false
            var hasNarratedInteraction: Boolean = false
            var hasVoiceControlInteraction: Boolean = false
            var hasSimplifiedInteraction: Boolean = false

            for (interactionMethod in eInteractionMethodsList) {
                when (interactionMethod) {
                    EInteractionMethods.Default -> hasDefaultInteraction = true
                    EInteractionMethods.Sequential -> hasSequentialInteraction = true
                    EInteractionMethods.TabbedNavigation -> hasTabbedNavigationInteraction = true
                    EInteractionMethods.Narrated -> hasNarratedInteraction = true
                    EInteractionMethods.VoiceControl -> hasVoiceControlInteraction = true
                    EInteractionMethods.Simplified -> hasSimplifiedInteraction = true
                }
            }

            return InteractionMethods(
                hasDefaultInteraction, hasSequentialInteraction, hasTabbedNavigationInteraction, hasNarratedInteraction, hasVoiceControlInteraction, hasSimplifiedInteraction
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
