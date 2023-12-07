package com.integrame.app.tasks.data.model

import com.integrame.app.core.data.model.content.DynamicContent
import com.integrame.app.core.data.model.content.ImageContent
import com.integrame.app.core.data.model.content.RemoteImage
import kotlinx.serialization.Serializable

/**
 * Clase que representa la información básica de una tarea de menú comedor.
 *
 * Se utiliza principalmente para la visualización.
 */
@Serializable
data class MenuTaskModel(
    override val taskId: Int,
    override val displayName: String,
    override val displayImage: ImageContent,
    override val reward: DynamicContent
): TaskModel(){
    companion object {
        fun fromMenuTask(task: MenuTask): MenuTaskModel {
            return MenuTaskModel(
                taskId = task.taskId,
                displayName = task.displayName,
                displayImage = task.displayImage,
                reward = task.reward
            )
        }
    }
}

// TODO: Revisar y ajustar todas las clases por debajo
@Serializable
data class MenuTask(
    override val taskId: Int,
    override val displayName: String,
    override val displayImage: ImageContent,
    override val reward: DynamicContent,
    val classroomMenus: List<ClassroomMenuTask>
): Task()

@Serializable
data class ClassroomMenuTask(
    val classroomId: Int, // Letra de la clase
    val menuOptions: List<MenuOption> // Lista de comida
)

@Serializable
data class MenuOption(
    val name: String, // Nombre de la comida
    val image: ImageContent // Imagen asociada a la comida
) {
    var requestedAmount: Int = 0 // Cantidad de comida escogida
        private set

    fun setRequestAmount(amount: Int) {
        requestedAmount = amount
    }
}
