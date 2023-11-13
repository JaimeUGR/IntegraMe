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