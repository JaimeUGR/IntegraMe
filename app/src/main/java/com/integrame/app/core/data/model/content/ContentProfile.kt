package com.integrame.app.core.data.model.content

/**
 * Engloba las adaptaciones al contenido para un usuario:
 * - Formatos de contenido
 * - Métodos de interacción con el contenido
 */
data class ContentProfile(
    val interactionMethods: UserInteractionMethods,
    val contentAdaptationFormats: UserContentAdaptationFormats
)
