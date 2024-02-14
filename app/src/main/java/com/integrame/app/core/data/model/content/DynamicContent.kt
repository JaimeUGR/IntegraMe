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

import android.graphics.Bitmap
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Engloba cualquier tipo de contenido dinámico que puede existir en la aplicación
 * y que puede ser mostrado a los usuarios
 */
@Serializable
sealed interface DynamicContent

/**
 * Representa contenido textual
 */
@Serializable
@SerialName("TextContent")
data class TextContent(val text: String): DynamicContent

/**
 * Representa contenido de imágenes con un id asociado
 */
@Serializable
sealed class ImageContent: DynamicContent {
    abstract val id: Int
    abstract val altDescription: String
}

/**
 * Representa imágenes locales (res)
 */
data class LocalImage(@DrawableRes override val id: Int, override val altDescription: String): ImageContent()

/**
 * Representa imágenes en formato BitMap
 */
data class BitMapImage(val bitmap: Bitmap, override val id: Int, override val altDescription: String): ImageContent()

/**
 * Representa imágenes vectoriales
 */
data class VectorImage(val vectorImage: ImageVector, override val id: Int, override val altDescription: String): ImageContent()

/**
 * Representa imágenes cargadas desde red (url)
 */
@Serializable
@SerialName("RemoteImage")
data class RemoteImage(val imageUrl: String, override val id: Int, override val altDescription: String): ImageContent()

/**
 * Representa vídeos cargados desde red (url)
 */
@Serializable
@SerialName("VideoContent")
data class VideoContent(val videoUrl: String, val id: Int): DynamicContent

/**
 * Representa audios cargados desde red (url)
 */
@Serializable
@SerialName("AudioContent")
data class AudioContent(val audioUrl: String, val id: Int): DynamicContent
