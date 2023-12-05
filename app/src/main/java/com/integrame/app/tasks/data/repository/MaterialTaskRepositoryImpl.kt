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
    override fun getTaskModel(taskId: Int): MaterialTaskModel {
        return MaterialTaskModel.fromMaterialTask(FakeResources.materialTasks[taskId])
    }

    override fun getNumRequests(taskId: Int): Int {
        return getTaskModel(taskId).requests
    }

    override fun getMaterialRequest(taskId: Int, requestId: Int): MaterialRequest {
        return FakeResources.materialTasks[taskId].request[requestId]
    }

    override fun toggleRequestDelivered(taskId: Int, requestId: Int): Boolean {
        val currentState = FakeResources.materialTasks[taskId].request[requestId].isDelivered
        FakeResources.materialTasks[taskId].request[requestId].isDelivered = !currentState

        return !currentState
    }

    override fun getReward(taskId: Int): DynamicContent {
        return FakeResources.materialTasks[taskId].reward
    }
}