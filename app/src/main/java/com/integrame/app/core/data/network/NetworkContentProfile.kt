package com.integrame.app.core.data.network

import com.integrame.app.core.data.model.content.ContentAdaptationFormats
import com.integrame.app.core.data.model.content.ContentProfile
import com.integrame.app.core.data.model.content.InteractionMethods
import com.integrame.app.core.data.model.content.UserContentAdaptationFormats
import com.integrame.app.core.data.model.content.UserInteractionMethods
import kotlinx.serialization.Serializable

@Serializable
data class NetworkContentProfile(
    val contentAdaptationFormats: List<ContentAdaptationFormats>,
    val interactionMethods: List<InteractionMethods>
)

fun NetworkContentProfile.toContentProfile() : ContentProfile {
    return ContentProfile(
        interactionMethods = UserInteractionMethods.fromEnumList(interactionMethods),
        contentAdaptationFormats = UserContentAdaptationFormats.fromEnumList(contentAdaptationFormats)
    )
}
