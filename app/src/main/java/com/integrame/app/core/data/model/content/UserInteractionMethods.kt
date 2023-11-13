package com.integrame.app.core.data.model.content

data class UserInteractionMethods(
    val hasDefaultInteraction: Boolean,
    val hasSequentialInteraction: Boolean,
    val hasTabbedNavigationInteraction: Boolean,
    val hasNarratedInteraction: Boolean,
    val hasVoiceControlInteraction: Boolean,
    val hasSimplifiedInteraction: Boolean
) {
    companion object {
        fun fromEnumList(interactionMethodsList: List<InteractionMethods>): UserInteractionMethods {
            var hasDefaultInteraction: Boolean = false
            var hasSequentialInteraction: Boolean = false
            var hasTabbedNavigationInteraction: Boolean = false
            var hasNarratedInteraction: Boolean = false
            var hasVoiceControlInteraction: Boolean = false
            var hasSimplifiedInteraction: Boolean = false

            for (interactionMethod in interactionMethodsList) {
                when (interactionMethod) {
                    InteractionMethods.Default -> hasDefaultInteraction = true
                    InteractionMethods.Sequential -> hasSequentialInteraction = true
                    InteractionMethods.TabbedNavigation -> hasTabbedNavigationInteraction = true
                    InteractionMethods.Narrated -> hasNarratedInteraction = true
                    InteractionMethods.VoiceControl -> hasVoiceControlInteraction = true
                    InteractionMethods.Simplified -> hasSimplifiedInteraction = true
                }
            }

            return UserInteractionMethods(
                hasDefaultInteraction = hasDefaultInteraction,
                hasSequentialInteraction = hasSequentialInteraction,
                hasTabbedNavigationInteraction = hasTabbedNavigationInteraction,
                hasNarratedInteraction = hasNarratedInteraction,
                hasVoiceControlInteraction = hasVoiceControlInteraction,
                hasSimplifiedInteraction = hasSimplifiedInteraction
            )
        }
    }
}
