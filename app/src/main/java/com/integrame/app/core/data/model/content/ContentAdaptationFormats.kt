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

import kotlinx.serialization.Serializable

@Serializable
enum class ContentAdaptationFormats {
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