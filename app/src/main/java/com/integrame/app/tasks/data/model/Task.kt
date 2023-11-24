package com.integrame.app.tasks.data.model

import com.integrame.app.core.data.model.content.ImageContent
import kotlinx.serialization.Serializable

@Serializable
enum class TaskType {
    Generic,
    FoodMenu,
    MaterialRequest
}

@Serializable
enum class TaskState {
    Failed,
    Pending,
    Completed
}

@Serializable
data class TaskCard(
    val taskId: Int,
    val taskState: TaskState,
    val displayName: String,
    val displayImage: ImageContent,
    val taskType: TaskType
)

@Serializable
abstract class Task {
    abstract val displayName: String
    abstract val displayImage: ImageContent
}
