package com.integrame.app.tasks.data.repository

import com.integrame.app.core.data.fake.FakeResources
import com.integrame.app.core.data.model.content.DynamicContent
import com.integrame.app.core.data.network.api.IntegraMeApi
import com.integrame.app.tasks.data.model.GenericTask
import com.integrame.app.tasks.data.model.GenericTaskModel
import com.integrame.app.tasks.data.model.GenericTaskStep
import com.integrame.app.tasks.domain.repository.GenericTaskRepository

class GenericTaskRepositoryImpl(
    private val api: IntegraMeApi
): GenericTaskRepository {
    // TODO: Incoporar AuthRequestResult
    override suspend fun getTaskModel(taskId: Int): GenericTaskModel {
        return api.getGenericTaskModel(taskId)
    }

    override suspend fun getStep(taskId: Int, stepNumber: Int): GenericTaskStep {
        return api.getGenericTaskStep(taskId, stepNumber)
    }

    override suspend fun toggleStepCompleted(taskId: Int, stepNumber: Int): Boolean {
        val step = getStep(taskId, stepNumber)
        step.isCompleted = !step.isCompleted

        api.toggleGenericTaskStepCompleted(taskId, stepNumber, step.isCompleted)

        return step.isCompleted
    }
}
