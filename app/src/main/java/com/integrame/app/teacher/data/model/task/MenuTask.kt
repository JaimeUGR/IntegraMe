package com.integrame.app.teacher.data.model.task

import com.integrame.app.core.data.model.content.ImageContent
import com.integrame.app.core.data.model.content.RemoteImage
import kotlinx.serialization.Serializable

@Serializable
data class MenuTask(
    override var displayName: String,
    override var displayImage: RemoteImage,
    val classroomMenus: List<ClassroomMenuTask>
): Task(){

    // Setter para displayName en MenuTask
    fun setDisplayName(newDisplayName: String) {
        // Puedes hacer validaciones o lógica adicional aquí si es necesario
        // Asignar el nuevo valor
        displayName = newDisplayName
    }

    // Setter para displayImage en MenuTask
    fun setDisplayImage(newDisplayImage: RemoteImage) {
        // Puedes hacer validaciones o lógica adicional aquí si es necesario
        // Asignar el nuevo valor
        displayImage = newDisplayImage
    }

    // Método para actualizar ClassroomMenuTask
    fun updateClassroomMenu(classroomId: Int, newMenuOptions: List<MenuOption>): MenuTask {
        val updatedClassroomMenus = classroomMenus.map {
            if (it.classroomId == classroomId) {
                ClassroomMenuTask(classroomId, newMenuOptions)
            } else {
                it
            }
        }

        return copy(classroomMenus = updatedClassroomMenus)
    }

}

@Serializable
data class ClassroomMenuTask(
    val classroomId: Int,
    val menuOptions: List<MenuOption>
)

@Serializable
data class MenuOption(
    val name: String,
    val image: RemoteImage
)
