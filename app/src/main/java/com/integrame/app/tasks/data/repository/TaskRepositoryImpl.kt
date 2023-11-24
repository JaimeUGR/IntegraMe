package com.integrame.app.tasks.data.repository

import com.integrame.app.core.data.local.entities.UserType
import com.integrame.app.core.data.network.toSession
import com.integrame.app.core.util.AuthRequestResult
import com.integrame.app.core.util.RequestResult
import com.integrame.app.login.data.network.SignInTeacherRequest
import com.integrame.app.login.data.network.toIdentityCard
import com.integrame.app.tasks.data.model.MenuTask
import com.integrame.app.tasks.data.model.Task
import com.integrame.app.tasks.data.model.TaskCard
import com.integrame.app.tasks.domain.repository.TaskRepository
import retrofit2.HttpException
import com.integrame.app.core.data.network.api.IntegraMeApi
import com.integrame.app.tasks.data.model.GenericTask
import com.integrame.app.tasks.data.model.MaterialTask


class TaskRepositoryImpl(private val api: IntegraMeApi): TaskRepository {
    override suspend fun getPendingTasks(): List<TaskCard> {
        return emptyList()
    }

    override suspend fun getTask(taskId: Int): AuthRequestResult<Task> {
        return try {
            AuthRequestResult.Authorized(api.getTask(taskId))
        } catch (e: HttpException) {
            val statusCode = e.code()
            AuthRequestResult.Error("Error code: $statusCode")
        } catch (e: Exception) {
            AuthRequestResult.Error("Error: ${e.message ?: " desconocido"}")
        }
    }

    override suspend fun getGenericTask(taskId: Int): AuthRequestResult<GenericTask> {
        return when (val requestResult = getTask(taskId)) {
            is AuthRequestResult.Authorized -> {
                if (requestResult.data is GenericTask)
                    AuthRequestResult.Authorized(requestResult.data)
                else
                    AuthRequestResult.Error("Error: la tarea devuelta es del tipo ${requestResult.data::class}")
            }
            is AuthRequestResult.Unauthorized -> AuthRequestResult.Unauthorized()
            is AuthRequestResult.Error -> AuthRequestResult.Error(requestResult.error)
        }
    }

    override suspend fun getMenuTask(taskId: Int): AuthRequestResult<MenuTask> {
        return when (val requestResult = getTask(taskId)) {
            is AuthRequestResult.Authorized -> {
                if (requestResult.data is MenuTask)
                    AuthRequestResult.Authorized(requestResult.data)
                else
                    AuthRequestResult.Error("Error: la tarea devuelta es del tipo ${requestResult.data::class}")
            }
            is AuthRequestResult.Unauthorized -> AuthRequestResult.Unauthorized()
            is AuthRequestResult.Error -> AuthRequestResult.Error(requestResult.error)
        }
    }

    override suspend fun getMaterialTask(taskId: Int): AuthRequestResult<MaterialTask> {
        return when (val requestResult = getTask(taskId)) {
            is AuthRequestResult.Authorized -> {
                if (requestResult.data is MaterialTask)
                    AuthRequestResult.Authorized(requestResult.data)
                else
                    AuthRequestResult.Error("Error: la tarea devuelta es del tipo ${requestResult.data::class}")
            }
            is AuthRequestResult.Unauthorized -> AuthRequestResult.Unauthorized()
            is AuthRequestResult.Error -> AuthRequestResult.Error(requestResult.error)
        }
    }
}
