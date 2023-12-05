package com.integrame.app.teacher.data.model.task

import com.integrame.app.core.data.model.content.ImageContent

data class MaterialTask(
    override val displayName: String,
    override val displayImage: ImageContent,
    val request: List<MaterialRequest>
): Task(){
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

data class MaterialRequest(
    val material: Material,
    val amount: Int,
)

data class Material(
    val displayImage: ImageContent,
    val properties: List<Int> // TODO: Similar al DynamicContent, para representar contenido o tamaño
)
