package com.integrame.app.teacher.data.model.task

import com.integrame.app.core.data.model.content.ImageContent
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
    val startDate : Long?,
    val dueDate: Long?,
    val task: Task?,
    val taskType: TaskType?,
    val reward: Int?//TODO: se pondrá cuando este implementada la clase de las recompensas
) {
    val code: String = ""
    val isSuccessful: Boolean = false
}

@Serializable
abstract class Task {
    abstract val displayName: String
    abstract val displayImage: ImageContent
}
