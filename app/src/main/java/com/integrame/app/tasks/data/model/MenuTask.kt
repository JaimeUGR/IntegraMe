package com.integrame.app.tasks.data.model

import com.integrame.app.core.data.model.content.DynamicContent
import com.integrame.app.core.data.model.content.ImageContent
import com.integrame.app.core.data.model.content.TextContent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Clase que representa la información básica de una tarea de menú comedor.
 *
 * Se utiliza principalmente para la visualización.
 */
// TODO: Quizás sería conveniente eliminar la integración con la List<Classrooms>
@Serializable
@SerialName("MenuTaskModel")
data class MenuTaskModel(
    override val taskId: Int,
    override val displayName: String,
    override val displayImage: ImageContent,
    override val reward: DynamicContent,
    val classrooms: List<Classroom>
): TaskModel()

@Serializable
data class Classroom(
    val classroomId: Int,
    val displayText: TextContent,
    val displayImage: ImageContent,
)

@Serializable
data class ClassroomMenu(
    val classroom: Classroom,
    val menuOptions: List<MenuOption>
)

@Serializable
data class MenuOption(
    val menuOptionId: Int,
    val displayName: TextContent, // Nombre de la comida
    val displayImage: ImageContent, // Imagen asociada a la comida
    var requestedAmount: Int
)
