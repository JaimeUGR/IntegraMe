package com.integrame.app.tasks.domain.repository

import com.integrame.app.core.util.AuthRequestResult
import com.integrame.app.tasks.data.model.GenericTask
import com.integrame.app.tasks.data.model.MaterialTask
import com.integrame.app.tasks.data.model.MenuTask
import com.integrame.app.tasks.data.model.Task
import com.integrame.app.tasks.data.model.TaskCard

interface TaskRepository {
    suspend fun getPendingTasks(): List<TaskCard>
    suspend fun getTask(taskId: Int): AuthRequestResult<Task>
    suspend fun getGenericTask(taskId: Int): AuthRequestResult<GenericTask>
    suspend fun getMenuTask(taskId: Int): AuthRequestResult<MenuTask>
    suspend fun getMaterialTask(taskId: Int): AuthRequestResult<MaterialTask>
}
