package com.integrame.app.tasks.data.model

import com.integrame.app.core.data.model.content.ImageContent
import com.integrame.app.core.data.model.content.RemoteImage
import kotlinx.serialization.Serializable

@Serializable
data class MenuTask(
    override val displayName: String,
    override val displayImage: ImageContent,
    val classroomMenus: List<ClassroomMenuTask>
): Task()

@Serializable
data class ClassroomMenuTask(
    val classroomId: Int,
    val menuOptions: List<MenuOption>
)

@Serializable
data class MenuOption(
    val name: String,
    val image: ImageContent
) {
    var requestedAmount: Int = 0
        private set

    fun setRequestedAmount(nuevoValor: Int) {
        requestedAmount = nuevoValor
    }

}
