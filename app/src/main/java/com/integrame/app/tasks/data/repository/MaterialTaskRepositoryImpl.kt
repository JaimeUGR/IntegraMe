package com.integrame.app.tasks.data.repository

import com.integrame.app.core.data.fake.FakeResources
import com.integrame.app.core.data.model.content.DynamicContent
import com.integrame.app.core.data.network.api.IntegraMeApi
import com.integrame.app.tasks.data.model.MaterialRequest
import com.integrame.app.tasks.data.model.MaterialTaskModel
import com.integrame.app.tasks.domain.repository.MaterialTaskRepository
import javax.inject.Inject

class MaterialTaskRepositoryImpl @Inject constructor(
    private val api: IntegraMeApi
): MaterialTaskRepository {
    // TODO: incoporar AuthRequestResult
    override suspend fun getTaskModel(taskId: Int): MaterialTaskModel {
        return api.getMaterialTaskModel(taskId)
    }

    override suspend fun getMaterialRequest(taskId: Int, requestId: Int): MaterialRequest {
        return api.getMaterialTaskRequest(taskId, requestId)
    }

    override suspend fun toggleRequestDelivered(taskId: Int, requestId: Int): Boolean {
        val currentState = api.getMaterialTaskRequest(taskId, requestId).isDelivered

        api.toggleMaterialRequestDelivered(taskId, requestId, !currentState)

        return !currentState
    }
}