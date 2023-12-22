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
