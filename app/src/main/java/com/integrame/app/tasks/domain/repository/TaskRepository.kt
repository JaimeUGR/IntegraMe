package com.integrame.app.tasks.domain.repository

import com.integrame.app.core.util.AuthRequestResult
import com.integrame.app.tasks.data.model.GenericTask
import com.integrame.app.tasks.data.model.MaterialTask
import com.integrame.app.tasks.data.model.MenuTask
import com.integrame.app.tasks.data.model.Task
import com.integrame.app.tasks.data.model.TaskCard
import com.integrame.app.tasks.data.model.TaskModel

interface TaskRepository {
    suspend fun getTaskCards(): AuthRequestResult<List<TaskCard>>
    suspend fun getTaskModel(taskId: Int): AuthRequestResult<TaskModel>
}
