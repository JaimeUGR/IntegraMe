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
