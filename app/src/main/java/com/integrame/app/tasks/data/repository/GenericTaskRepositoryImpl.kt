package com.integrame.app.tasks.data.repository

import com.integrame.app.core.data.fake.FakeResources
import com.integrame.app.core.data.model.content.DynamicContent
import com.integrame.app.tasks.data.model.GenericTask
import com.integrame.app.tasks.data.model.GenericTaskModel
import com.integrame.app.tasks.data.model.GenericTaskStep
import com.integrame.app.tasks.domain.repository.GenericTaskRepository

class GenericTaskRepositoryImpl(

): GenericTaskRepository {
    override fun getTaskModel(taskId: Int): GenericTaskModel {
        return GenericTaskModel(
            taskId = taskId,
            displayName = FakeResources.genericTasks[taskId].displayName,
            displayImage = FakeResources.genericTasks[taskId].displayImage,
            steps = FakeResources.genericTasks[taskId].steps.size
        )
    }

    override fun getNumSteps(taskId: Int): Int {
        return FakeResources.genericTasks[taskId].steps.size
    }

    override fun getStep(taskId: Int, stepNumber: Int): GenericTaskStep {
        return FakeResources.genericTasks[taskId].steps[stepNumber]
    }

    override fun toggleStepCompleted(taskId: Int, stepNumber: Int): Boolean {
        val step = getStep(taskId, stepNumber)
        step.isCompleted = !step.isCompleted
        return step.isCompleted
    }

    override fun getReward(taskId: Int): DynamicContent {
        return FakeResources.genericTasks[taskId].reward
    }
}
