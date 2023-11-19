package com.integrame.app.tasks.data.model

import com.integrame.app.core.data.model.content.ImageContent

enum class TaskType {
    Generic,
    FoodMenu,
    MaterialRequest
}

enum class TaskState {
    Failed,
    Pending,
    Completed
}

data class TaskCard(
    val taskId: Int,
    val taskState: TaskState,
    val displayName: String,
    val displayImage: ImageContent,
    val taskType: TaskType
)

abstract class Task {
    abstract val displayName: String
    abstract val displayImage: ImageContent
}
