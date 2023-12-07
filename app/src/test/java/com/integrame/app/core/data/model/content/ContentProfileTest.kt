package com.integrame.app.core.data.model.content

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Test
import org.junit.Assert.*

class ContentProfileTest {
    val contentAdaptationFormats = listOf(ContentAdaptationFormats.Text, ContentAdaptationFormats.Image)
    val interactionMethods = listOf(InteractionMethods.Default, InteractionMethods.Simplified)

    val userContentAdaptationFormats: UserContentAdaptationFormats
    val userInteractionMethods: UserInteractionMethods

    init {
        userContentAdaptationFormats = UserContentAdaptationFormats.fromEnumList(contentAdaptationFormats)
        userInteractionMethods = UserInteractionMethods.fromEnumList(interactionMethods)
    }

    @Test
    fun testSerializeContentProfile() {
        val contentProfile = ContentProfile(userContentAdaptationFormats, userInteractionMethods)
        assertEquals("""{"contentAdaptationFormats":["Text","Image"],"interactionMethods":["Default","Simplified"]}""", Json.encodeToString(contentProfile))
    }

    @Test
    fun testDeserializeContentProfile() {
        val contentProfile = Json.decodeFromString<ContentProfile>("""{"contentAdaptationFormats":["Text","Image"],"interactionMethods":["Default","Simplified"]}""")
        assert(contentProfile.contentAdaptationFormats.hasImageContent)
        assert(!contentProfile.contentAdaptationFormats.hasVideoContent)
        assert(contentProfile.interactionMethods.hasSimplifiedInteraction)
        assert(!contentProfile.interactionMethods.hasTabbedNavigationInteraction)
    }
}