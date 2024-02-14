/*
 *
 * Copyright (c) IntegraMe - Champions 2024 - All rights reserved
 *
 *  You may use, distribute and modify this code under the terms of the
 *  Creative Commons Attribution-NonCommercial 4.0 International (CC BY-NC 4.0) license.
 *
 *  You should have received a copy of the CC BY-NC 4.0 license. Otherwise, read
 *  the full license text here: https://creativecommons.org/licenses/by-nc/4.0/legalcode
 *
 *  This file belongs to the project IntegraMe: https://github.com/JaimeUGR/IntegraMe
 */

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
