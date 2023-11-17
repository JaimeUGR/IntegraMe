package com.integrame.app.tasks.data.repository

import com.integrame.app.tasks.data.model.Task
import com.integrame.app.tasks.data.model.TaskCard
import com.integrame.app.tasks.domain.repository.TaskRepository

class TaskRepositoryImpl: TaskRepository{
    suspend fun getTask(taskId: Int): Task {
        TODO()
    }

    suspend fun getPendingTasks(): List<TaskCard> {
        return emptyList()
    }
}
