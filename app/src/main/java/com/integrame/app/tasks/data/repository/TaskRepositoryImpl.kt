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
