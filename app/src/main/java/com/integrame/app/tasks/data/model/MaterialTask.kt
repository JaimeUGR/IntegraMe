package com.integrame.app.tasks.data.model

import com.integrame.app.core.data.model.content.ImageContent
import kotlinx.serialization.Serializable

data class MaterialTaskModel(
    override val taskId: Int,
    override val displayName: String,
    override val displayImage: ImageContent
): TaskModel()

@Serializable
data class MaterialTask(
    override val taskId: Int,
    override val displayName: String,
    override val displayImage: ImageContent,
    val request: List<MaterialRequest>
): Task()

@Serializable
data class MaterialRequest(
    val material: Material,
    val amount: Int,
    val displayAmount: ImageContent,
    var isDelivered: Boolean
)

@Serializable
data class Material(
    val displayImage: ImageContent,
    val properties: List<Int> // TODO: Similar al DynamicContent, para representar contenido o tamaño
)
