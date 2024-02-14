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

import com.integrame.app.core.util.AuthRequestResult
import com.integrame.app.tasks.data.model.TaskCard
import com.integrame.app.tasks.domain.repository.TaskRepository
import retrofit2.HttpException
import com.integrame.app.core.data.network.api.IntegraMeApi
import com.integrame.app.tasks.data.model.TaskModel

class TaskRepositoryImpl(private val api: IntegraMeApi): TaskRepository {
    // TODO: Incoporar AuthRequestResult
    override suspend fun getTaskCards(): AuthRequestResult<List<TaskCard>> {
        return AuthRequestResult.Authorized(api.getTaskCards())
    }

    override suspend fun getTaskModel(taskId: Int): AuthRequestResult<TaskModel> {
        return try {
            AuthRequestResult.Authorized(api.getTaskModel(taskId))
        } catch (e: HttpException) {
            val statusCode = e.code()

            return if (statusCode == 401)
                AuthRequestResult.Unauthorized()
            else
                AuthRequestResult.Error("Error code: $statusCode")
        } catch (e: Exception) {
            AuthRequestResult.Error("Error: ${e.message ?: " desconocido"}")
        }
    }
}
