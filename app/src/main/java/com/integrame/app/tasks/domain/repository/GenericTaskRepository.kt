package com.integrame.app.tasks.domain.repository

import com.integrame.app.core.data.model.content.DynamicContent
import com.integrame.app.tasks.data.model.GenericTask
import com.integrame.app.tasks.data.model.GenericTaskModel
import com.integrame.app.tasks.data.model.GenericTaskStep

interface GenericTaskRepository {
    fun getTaskModel(taskId: Int): GenericTaskModel
    fun getNumSteps(taskId: Int): Int

    fun getStep(taskId: Int, stepNumber: Int): GenericTaskStep
    fun toggleStepCompleted(taskId: Int, stepNumber: Int): Boolean

    fun getReward(taskId: Int): DynamicContent
}