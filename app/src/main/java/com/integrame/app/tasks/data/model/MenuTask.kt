package com.integrame.app.tasks.data.model

import com.integrame.app.core.data.model.content.ImageContent
import com.integrame.app.core.data.model.content.RemoteImage
import kotlinx.serialization.Serializable

data class MenuTaskModel(
    override val taskId: Int,
    override val displayName: String,
    override val displayImage: ImageContent
): TaskModel()

@Serializable
data class MenuTask(
    override val taskId: Int,
    override val displayName: String,
    override val displayImage: RemoteImage,
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
    val image: RemoteImage
) {
    var requestedAmount: Int = 0
        private set
}
