package com.integrame.app.core.data.network

import com.integrame.app.core.data.model.content.ContentAdaptationFormats
import com.integrame.app.core.data.model.content.ContentProfile
import com.integrame.app.core.data.model.content.EContentAdaptationFormats
import com.integrame.app.core.data.model.content.EInteractionMethods
import com.integrame.app.core.data.model.content.InteractionMethods
import kotlinx.serialization.Serializable

@Serializable
data class NetworkContentProfile(
    val contentAdaptationFormats: List<EContentAdaptationFormats>,
    val interactionMethods: List<EInteractionMethods>
)

fun NetworkContentProfile.toContentProfile() : ContentProfile {
    return ContentProfile(
        interactionMethods = InteractionMethods.fromEnumList(interactionMethods),
        contentAdaptationFormats = ContentAdaptationFormats.fromEnumList(contentAdaptationFormats)
    )
}
