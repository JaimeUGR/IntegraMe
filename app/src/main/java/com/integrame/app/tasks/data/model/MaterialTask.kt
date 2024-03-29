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

package com.integrame.app.tasks.data.model

import com.integrame.app.core.data.model.content.DynamicContent
import com.integrame.app.core.data.model.content.ImageContent
import com.integrame.app.core.data.model.content.TextContent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Representa la información básica de una tarea petición de material. Se utiliza principalmente
 * para la visualización.
 */
@Serializable
@SerialName("MaterialTaskModel")
data class MaterialTaskModel(
    override val taskId: Int,
    override val displayName: String,
    override val displayImage: ImageContent,
    override val reward: DynamicContent,
    val requests: Int
): TaskModel() {
    companion object {
        fun fromMaterialTask(task: MaterialTask): MaterialTaskModel {
            return MaterialTaskModel(
                taskId = task.taskId,
                displayName = task.displayName,
                displayImage = task.displayImage,
                reward = task.reward,
                requests = task.request.size
            )
        }
    }
}

/**
 * Clase que representa la petición de un material
 */
@Serializable
data class MaterialRequest(
    val material: Material,
    val displayAmount: ImageContent,
    var isDelivered: Boolean
)

/**
 * Clase que representa la información de un material
 */
@Serializable
data class Material(
    val displayName: TextContent,
    val displayImage: ImageContent,
    val property: MaterialProperty?
)

/**
 * Clase que representa una propiedad de un material
 */
@Serializable
data class MaterialProperty(
    val displayName: TextContent,
    val displayImage: ImageContent
)

/**
 * Clase que representa la información completa de una tarea de tipo petición material.
 *
 * Se utiliza principalmente para la edición y visualización de una tarea ya asignada.
 */
@Serializable
data class MaterialTask(
    override val taskId: Int,
    override val displayName: String,
    override val displayImage: ImageContent,
    override val reward: DynamicContent,
    val request: List<MaterialRequest>
): Task()
