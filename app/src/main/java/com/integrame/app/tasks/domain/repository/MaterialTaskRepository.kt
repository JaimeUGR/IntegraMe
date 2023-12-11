package com.integrame.app.tasks.domain.repository

import com.integrame.app.core.data.model.content.DynamicContent
import com.integrame.app.tasks.data.model.MaterialRequest
import com.integrame.app.tasks.data.model.MaterialTaskModel

interface MaterialTaskRepository {
    suspend fun getTaskModel(taskId: Int): MaterialTaskModel
    suspend fun getMaterialRequest(taskId: Int, requestId: Int): MaterialRequest
    suspend fun toggleRequestDelivered(taskId: Int, requestId: Int): Boolean
}