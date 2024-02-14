/*
 *
 * Copyright (c) IntegraMe - Champions 2024 - All rights reserved
 *
 *  You may use, distribute and modify this code under the terms of the
 *  Creative Commons Attribution-NonCommercial 4.0 International (CC BY-NC 4.0) license.
 *
 *  You should have received a copy of the CC BY-NC 4.0 license. Otherwise, read
 *  the full license text here: https://creativecommons.org/licenses/by-nc/4.0/legalcode
 *
 *  This file belongs to the project IntegraMe: https://github.com/JaimeUGR/IntegraMe
 */

package com.integrame.app.core.data.model.content

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure
import kotlinx.serialization.encoding.encodeStructure

/**
 * Engloba las adaptaciones al contenido para un usuario:
 * - Formatos de contenido
 * - Métodos de interacción con el contenido
 */
@Serializable(with = ContentProfileSerializer::class)
data class ContentProfile(
    val contentAdaptationFormats: UserContentAdaptationFormats,
    val interactionMethods: UserInteractionMethods
) {
    fun getContentAdaptationFormatsAsList(): List<ContentAdaptationFormats> {
        val resultList = mutableListOf<ContentAdaptationFormats>()

        if (contentAdaptationFormats.hasTextContent)
            resultList.add(ContentAdaptationFormats.Text)

        if (contentAdaptationFormats.hasImageContent)
            resultList.add(ContentAdaptationFormats.Image)

        if (contentAdaptationFormats.hasAudioContent)
            resultList.add(ContentAdaptationFormats.Audio)

        if (contentAdaptationFormats.hasVideoContent)
            resultList.add(ContentAdaptationFormats.Video)

        return resultList
    }

    fun getInteractionMethodsAsList(): List<InteractionMethods> {
        val resultList = mutableListOf<InteractionMethods>()

        if (interactionMethods.hasDefaultInteraction)
            resultList.add(InteractionMethods.Default)

        if (interactionMethods.hasNarratedInteraction)
            resultList.add(InteractionMethods.Narrated)

        if (interactionMethods.hasSequentialInteraction)
            resultList.add(InteractionMethods.Sequential)

        if (interactionMethods.hasTabbedNavigationInteraction)
            resultList.add(InteractionMethods.TabbedNavigation)

        if (interactionMethods.hasSimplifiedInteraction)
            resultList.add(InteractionMethods.Simplified)

        if (interactionMethods.hasVoiceControlInteraction)
            resultList.add(InteractionMethods.VoiceControl)

        return resultList
    }
}

object ContentProfileSerializer: KSerializer<ContentProfile> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("ContentProfile") {
        element<List<ContentAdaptationFormats>>("contentAdaptationFormats")
        element<List<InteractionMethods>>("interactionMethods")
    }

    override fun serialize(encoder: Encoder, value: ContentProfile) {
        encoder.encodeStructure(descriptor) {
            encodeSerializableElement(
                descriptor,
                0,
                ListSerializer(ContentAdaptationFormats.serializer()),
                value.getContentAdaptationFormatsAsList()
            )
            encodeSerializableElement(
                descriptor,
                1,
                ListSerializer(InteractionMethods.serializer()),
                value.getInteractionMethodsAsList()
            )
        }
    }

    override fun deserialize(decoder: Decoder): ContentProfile {
        return decoder.decodeStructure(descriptor) {
            var contentAdaptationFormats: List<ContentAdaptationFormats> = emptyList()
            var interactionMethods: List<InteractionMethods> = emptyList()

            loop@ while (true) {
                when (val index = decodeElementIndex(descriptor)) {
                    CompositeDecoder.DECODE_DONE -> break@loop
                    0 -> contentAdaptationFormats = decodeSerializableElement(descriptor, 0, ListSerializer(ContentAdaptationFormats.serializer()))
                    1 -> interactionMethods = decodeSerializableElement(descriptor, 1, ListSerializer(InteractionMethods.serializer()))
                    else -> throw SerializationException("Unexpected index $index")
                }
            }

            ContentProfile(
                contentAdaptationFormats = UserContentAdaptationFormats.fromEnumList(contentAdaptationFormats),
                interactionMethods = UserInteractionMethods.fromEnumList(interactionMethods)
            )
        }
    }
}
