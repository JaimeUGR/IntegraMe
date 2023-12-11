package com.integrame.app.tasks.domain.repository

import com.integrame.app.core.data.model.content.DynamicContent
import com.integrame.app.tasks.data.model.GenericTask
import com.integrame.app.tasks.data.model.GenericTaskModel
import com.integrame.app.tasks.data.model.GenericTaskStep

interface GenericTaskRepository {
    suspend fun getTaskModel(taskId: Int): GenericTaskModel
    suspend fun getStep(taskId: Int, stepNumber: Int): GenericTaskStep
    suspend fun toggleStepCompleted(taskId: Int, stepNumber: Int): Boolean
}
