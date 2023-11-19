package com.integrame.app.tasks.data.model

import com.integrame.app.core.data.model.content.ImageContent

data class MenuTask(
    override val displayName: String,
    override val displayImage: ImageContent,
    val classroomMenus: List<ClassroomMenuTask>
): Task()

data class ClassroomMenuTask(
    val classroomId: Int,
    val menuOptions: List<MenuOption>
)

data class MenuOption(
    val name: String,
    val image: ImageContent
) {
    var requestedAmount: Int = 0
        private set
}
