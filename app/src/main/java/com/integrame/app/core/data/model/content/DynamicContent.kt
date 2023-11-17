package com.integrame.app.core.data.model.content

import android.graphics.Bitmap
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

/**
 * Engloba cualquier tipo de contenido dinámico que puede existir en la aplicación
 * y que puede ser mostrado a los usuarios
 */
interface DynamicContent

/**
 * Representa contenido textual
 */
data class TextContent(val text: String): DynamicContent

/**
 * Representa contenido de imágenes con un id asociado
 */
sealed class ImageContent: DynamicContent { abstract val id: Int }

/**
 * Representa imágenes locales (res)
 */
data class LocalImage(@DrawableRes override val id: Int, @StringRes val altDescription: Int): ImageContent()

/**
 * Representa imágenes en formato BitMap
 */
data class BitMapImage(val bitmap: Bitmap, override val id: Int, val altDescription: String): ImageContent()

/**
 * Representa imágenes vectoriales
 */
data class VectorImage(val vectorImage: ImageVector, override val id: Int, val altDescription: String): ImageContent()

/**
 * Representa imágenes cargadas desde red (url)
 */
@Serializable
// TODO: Ya no existirán imágenes remotas de red, sino que desde la api nos la formatearán con la url o crearán la url
data class RemoteImage(val imageUrl: String, override val id: Int, val altDescription: String): ImageContent()

/**
 * Representa vídeos cargados desde red (url)
 */
data class VideoContent(val videoUrl: String, val id: Int): DynamicContent

/**
 * Representa audios cargados desde red (url)
 */
data class AudioContent(val audioUrl: String, val id: Int): DynamicContent
