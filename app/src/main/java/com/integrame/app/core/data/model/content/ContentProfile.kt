package com.integrame.app.core.data.model.content

/**
 * Engloba las adaptaciones al contenido para un usuario:
 * - Formatos de contenido
 * - Métodos de interacción con el contenido
 */
data class ContentProfile(
    val interactionMethods: UserInteractionMethods,
    val contentAdaptationFormats: UserContentAdaptationFormats
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
}
