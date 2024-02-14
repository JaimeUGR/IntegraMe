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

package com.integrame.app.teacher.data.model.task

import com.integrame.app.core.data.model.content.ImageContent
import com.integrame.app.core.data.model.content.TextContent
import kotlinx.serialization.Serializable

@Serializable
data class MaterialTask(
    override val displayName: String,
    override val displayImage: ImageContent,
    val request: List<MaterialRequest>
): Task() {
    // Métodos para realizar las actualizaciones en MaterialTask
    fun setDisplayName(newDisplayName: String): MaterialTask {
        return copy(displayName = newDisplayName)
    }

    fun setDisplayImage(newDisplayImage: ImageContent): MaterialTask {
        return copy(displayImage = newDisplayImage)
    }

    fun updateMaterialRequests(newRequests: List<MaterialRequest>): MaterialTask {
        return copy(request = newRequests)
    }
}

@Serializable
data class MaterialRequest(
    val material: Material,
    val amount: Int,
)

@Serializable
data class Material(
    val displayName: String,
    val displayImage: ImageContent,
    val property: MaterialProperty
)

@Serializable
data class MaterialProperty(
    val displayName: TextContent,
    val displayImage: ImageContent
)
