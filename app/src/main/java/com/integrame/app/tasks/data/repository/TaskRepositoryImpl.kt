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


class TaskRepositoryImpl(private val api: IntegraMeApi) : TaskRepository {

    suspend fun getTask(taskId: Int): Task {
        TODO()
    }

    suspend fun getPendingTasks(): List<TaskCard> {
        return emptyList()
    }

    suspend fun getMenuTask(taskId: Int): AuthRequestResult<MenuTask> {
        return try {
            AuthRequestResult.Authorized(api.getMenuTask(taskId))
        } catch (e: HttpException) {
            val statusCode = e.code()
            AuthRequestResult.Error("Error code: $statusCode")
        } catch (e: Exception) {
            AuthRequestResult.Error("Error: ${e.message ?: " desconocido"}")
        }


    }
}
