package com.integrame.app.tasks.data.model

import com.integrame.app.core.data.model.content.ContentPack
import com.integrame.app.core.data.model.content.DynamicContent
import com.integrame.app.core.data.model.content.ImageContent
import com.integrame.app.core.data.model.content.RemoteImage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Representa la información básica de la tarea a realizar.
 *
 * Se utiliza para la visualización de la tarea.
 */
@Serializable
@SerialName("GenericTaskModel")
data class GenericTaskModel(
    override val taskId: Int,
    override val displayName: String,
    override val displayImage: ImageContent,
    override val reward: DynamicContent,
    val steps: Int
): TaskModel() {
    companion object {
        fun fromGenericTask(task: GenericTask): GenericTaskModel {
            return GenericTaskModel(
                taskId = task.taskId,
                displayName = task.displayName,
                displayImage = task.displayImage,
                reward = task.reward,
                steps = task.steps.size
            )
        }
    }
}

/**
 * Representa la información de un paso de la tarea genérica.
 */
@Serializable
data class GenericTaskStep(
    val displayName: String,
    var isCompleted: Boolean,
    val content: ContentPack
)

/**
 * Representa la información completa de una tarea. Se utiliza para la visualización y edición
 * de los datos de una tarea ya creada.
 */
@Serializable
data class GenericTask(
    override val taskId: Int,
    override val displayName: String,
    override val displayImage: RemoteImage,
    override val reward: DynamicContent,
    val steps: List<GenericTaskStep>
): Task()
