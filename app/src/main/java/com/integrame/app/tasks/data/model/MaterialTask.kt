package com.integrame.app.tasks.data.model

import com.integrame.app.core.data.model.content.ImageContent

data class MaterialTask(
    override val displayName: String,
    override val displayImage: ImageContent,
    val request: List<MaterialRequest>
): Task()

data class MaterialRequest(
    val material: Material,
    val amount: Int,
    val displayAmount: ImageContent,
    var isDelivered: Boolean
)

data class Material(
    val displayImage: ImageContent,
    val properties: List<Int> // TODO: Similar al DynamicContent, para representar contenido o tamaño
)
