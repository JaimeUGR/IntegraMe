package com.integrame.app.tasks.data.repository

import com.integrame.app.core.data.fake.FakeResources
import com.integrame.app.core.util.AuthRequestResult
import com.integrame.app.tasks.data.model.MenuTask
import com.integrame.app.tasks.data.model.Task
import com.integrame.app.tasks.data.model.TaskCard
import com.integrame.app.tasks.domain.repository.TaskRepository
import retrofit2.HttpException
import com.integrame.app.core.data.network.api.IntegraMeApi
import com.integrame.app.tasks.data.model.GenericTask
import com.integrame.app.tasks.data.model.GenericTaskModel
import com.integrame.app.tasks.data.model.MaterialTask
import com.integrame.app.tasks.data.model.MaterialTaskModel
import com.integrame.app.tasks.data.model.MenuTaskModel
import com.integrame.app.tasks.data.model.TaskModel


class TaskRepositoryImpl(private val api: IntegraMeApi): TaskRepository {
    // TODO: Incorporar api
    override suspend fun getPendingTaskCards(): AuthRequestResult<List<TaskCard>> {
        return AuthRequestResult.Authorized(FakeResources.taskCards)
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

    override suspend fun getTaskModel(taskId: Int): AuthRequestResult<TaskModel> {
        return when (val requestResult = getTask(taskId)) {
            is AuthRequestResult.Authorized -> {
                AuthRequestResult.Authorized(
                    when(val task = requestResult.data) {
                        is GenericTask -> {
                            GenericTaskModel.fromGenericTask(task)
                        }
                        is MenuTask -> {
                            MenuTaskModel(
                                task.taskId,
                                task.displayName,
                                task.displayImage
                            )
                        }
                        is MaterialTask -> {
                            MaterialTaskModel(
                                task.taskId,
                                task.displayName,
                                task.displayImage,
                                task.request.size
                            )
                        }
                        else -> {
                            return AuthRequestResult.Error("Tipo de tarea desconocido: ${task.javaClass}")
                        }
                    }
                )
            }
            is AuthRequestResult.Error -> AuthRequestResult.Error(requestResult.error)
            is AuthRequestResult.Unauthorized -> AuthRequestResult.Unauthorized()
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
