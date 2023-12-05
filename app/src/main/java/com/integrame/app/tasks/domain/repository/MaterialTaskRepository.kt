package com.integrame.app.tasks.domain.repository

import com.integrame.app.core.data.model.content.DynamicContent
import com.integrame.app.tasks.data.model.MaterialRequest
import com.integrame.app.tasks.data.model.MaterialTaskModel

interface MaterialTaskRepository {
    fun getTaskModel(taskId: Int): MaterialTaskModel
    fun getNumRequests(taskId: Int): Int
    fun getMaterialRequest(taskId: Int, requestId: Int): MaterialRequest
    fun toggleRequestDelivered(taskId: Int, requestId: Int): Boolean
    fun getReward(taskId: Int): DynamicContent
}