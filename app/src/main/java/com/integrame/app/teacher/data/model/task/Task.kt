package com.integrame.app.teacher.data.model.task

import com.integrame.app.core.data.model.content.DynamicContent
import com.integrame.app.core.data.model.content.ImageContent
import com.integrame.app.tasks.data.model.TaskState
import kotlinx.serialization.Serializable

@Serializable
enum class TaskType {
    Generic,
    FoodMenu,
    MaterialRequest
}

@Serializable
data class TaskInfo(
    val assignedStudentId: Int?,
    val assignedTeachersIds: List<Int>,
    val description: String,
    val startDate : String,
    val dueDate: String,
    val task: Task?,
    val taskType: TaskType?,
    val reward: Int?//TODO: se pondrá cuando este implementada la clase de las recompensas
) {

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

@Serializable
abstract class TaskModel {
    abstract val taskId: Int
    abstract val displayName: String
    abstract val displayImage: ImageContent
    abstract val reward: DynamicContent
}
