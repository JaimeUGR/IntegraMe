package com.integrame.app.tasks.data.model

import com.integrame.app.core.data.model.content.DynamicContent
import com.integrame.app.core.data.model.content.ImageContent
import com.integrame.app.core.data.model.content.TextContent
import kotlinx.serialization.Serializable

data class MaterialTaskModel(
    override val taskId: Int,
    override val displayName: String,
    override val displayImage: ImageContent,
    val requests: Int
): TaskModel() {
    companion object {
        fun fromMaterialTask(task: MaterialTask): MaterialTaskModel {
            return MaterialTaskModel(
                taskId = task.taskId,
                displayName = task.displayName,
                displayImage = task.displayImage,
                requests = task.request.size
            )
        }
    }
}

@Serializable
data class MaterialTask(
    override val taskId: Int,
    override val displayName: String,
    override val displayImage: ImageContent,
    override val reward: DynamicContent,
    val request: List<MaterialRequest>
): Task()

@Serializable
data class MaterialRequest(
    val material: Material,
    val amount: Int, // TODO: No se utiliza esto
    val displayAmount: ImageContent,
    var isDelivered: Boolean
)

@Serializable
data class Material(
    val displayName: TextContent,
    val displayImage: ImageContent,
    val property: MaterialProperty?
)

@Serializable
data class MaterialProperty(
    val displayName: TextContent,
    val displayImage: ImageContent
)
