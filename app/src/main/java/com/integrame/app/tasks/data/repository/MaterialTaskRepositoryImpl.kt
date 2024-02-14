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

package com.integrame.app.tasks.data.repository

import com.integrame.app.core.data.fake.FakeResources
import com.integrame.app.core.data.model.content.DynamicContent
import com.integrame.app.core.data.network.api.IntegraMeApi
import com.integrame.app.tasks.data.model.MaterialRequest
import com.integrame.app.tasks.data.model.MaterialTaskModel
import com.integrame.app.tasks.data.network.NetworkPostMaterialRequestDelivered
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

        api.toggleMaterialRequestDelivered(taskId, requestId, NetworkPostMaterialRequestDelivered(isDelivered = !currentState))

        return !currentState
    }
}