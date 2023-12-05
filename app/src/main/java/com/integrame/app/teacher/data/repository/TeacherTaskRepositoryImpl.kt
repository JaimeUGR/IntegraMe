package com.integrame.app.teacher.data.repository

import com.integrame.app.core.data.model.content.ImageContent
import com.integrame.app.core.data.model.content.RemoteImage
import com.integrame.app.core.data.network.api.IntegraMeApi
import com.integrame.app.core.util.AuthRequestResult
import com.integrame.app.teacher.data.model.task.Task
import com.integrame.app.teacher.data.model.task.GenericTask
import com.integrame.app.teacher.data.model.task.GenericTaskStep
import com.integrame.app.teacher.data.model.task.MaterialRequest
import com.integrame.app.teacher.data.model.task.MaterialTask
import com.integrame.app.teacher.data.model.task.MenuOption
import com.integrame.app.teacher.data.model.task.MenuTask
import com.integrame.app.teacher.data.model.task.TaskInfo
import com.integrame.app.teacher.data.model.task.TaskType
import com.integrame.app.teacher.domain.repository.TeacherTaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.HttpException

// TODO: LifeCycle de ViewModel para Dagger
class TeacherTaskRepositoryImpl (
    private val api: IntegraMeApi
): TeacherTaskRepository {

    private val _taskInfoFlow = MutableStateFlow(getDefaultTaskInfo())

    // Exponer el flujo como solo lectura
    val taskInfoFlow: StateFlow<TaskInfo>
        get() = _taskInfoFlow

    private fun getDefaultTaskInfo(): TaskInfo {
        return TaskInfo(
            assignedStudentId = null,
            assignedTeachersIds = emptyList(),
            description = "",
            startDate = null,
            dueDate = null,
            task = null,
            taskType = null,
            reward = null
        )
    }

    init {
        // Inicializar flujo
        _taskInfoFlow.value = getDefaultTaskInfo()
    }

    // Métodos set para cada atributo
    fun setAssignedStudentId(assignedStudentId: Int) {
        _taskInfoFlow.value = _taskInfoFlow.value.copy(assignedStudentId = assignedStudentId)
    }

    fun setAssignedTeachersIds(assignedTeachersIds: List<Int>) {
        _taskInfoFlow.value = _taskInfoFlow.value.copy(assignedTeachersIds = assignedTeachersIds)
    }

    fun setDescription(description: String) {
        _taskInfoFlow.value = _taskInfoFlow.value.copy(description = description)
    }

    fun setStartDate(startDate: Long) {
        _taskInfoFlow.value = _taskInfoFlow.value.copy(startDate = startDate)
    }

    fun setDueDate(dueDate: Long) {
        _taskInfoFlow.value = _taskInfoFlow.value.copy(dueDate = dueDate)
    }
    fun setTaskType(taskType: TaskType) {
        _taskInfoFlow.value = _taskInfoFlow.value.copy(taskType = taskType)
    }

    fun setReward(reward: Int) {
        _taskInfoFlow.value = _taskInfoFlow.value.copy(reward = reward)
    }

    fun setTask(task: Task) {
        _taskInfoFlow.value = _taskInfoFlow.value.copy(task = task)
    }



    // Método en el repositorio para actualizar la información de la tarea de Menú
    fun updateMenuTask(displayName: String, displayImage: RemoteImage) {
        if (_taskInfoFlow.value.task is MenuTask) {
            val menuTask = _taskInfoFlow.value.task as MenuTask
            menuTask.setDisplayName(displayName)
            menuTask.setDisplayImage(displayImage)

            // Actualizar el flujo con la nueva tarea
            _taskInfoFlow.value = _taskInfoFlow.value.copy(task = menuTask)
        }
    }

    fun updateClassroomMenuTask(classroomId: Int, newMenuOptions: List<MenuOption>) {
        if (_taskInfoFlow.value.task is MenuTask) {
            val menuTask = _taskInfoFlow.value.task as MenuTask
            val updatedMenuTask = menuTask.updateClassroomMenu(classroomId, newMenuOptions)

            // Actualizar el flujo con la nueva tarea
            _taskInfoFlow.value = _taskInfoFlow.value.copy(task = updatedMenuTask)
        }
    }

    // Método en el repositorio para actualizar la información de la tarea genérica
    fun updateGenericTask(displayName: String, displayImage: ImageContent) {
        if (_taskInfoFlow.value.task is GenericTask) {
            val genericTask = _taskInfoFlow.value.task as GenericTask
            val updatedGenericTask = genericTask
                .setDisplayName(displayName)
                .setDisplayImage(displayImage)

            // Actualizar el flujo con la nueva tarea
            _taskInfoFlow.value = _taskInfoFlow.value.copy(task = updatedGenericTask)
        }
    }

    fun updateGenericTaskSteps(newSteps: List<GenericTaskStep>) {
        if (_taskInfoFlow.value.task is GenericTask) {
            val genericTask = _taskInfoFlow.value.task as GenericTask
            val updatedGenericTask = genericTask.updateSteps(newSteps)

            // Actualizar el flujo con la nueva tarea
            _taskInfoFlow.value = _taskInfoFlow.value.copy(task = updatedGenericTask)
        }
    }

    // Método en el repositorio para actualizar la información de la tarea de material
    fun updateMaterialTask(displayName: String, displayImage: ImageContent) {
        if (_taskInfoFlow.value.task is MaterialTask) {
            val materialTask = _taskInfoFlow.value.task as MaterialTask
            val updatedMaterialTask = materialTask
                .setDisplayName(displayName)
                .setDisplayImage(displayImage)

            // Actualizar el flujo con la nueva tarea
            _taskInfoFlow.value = _taskInfoFlow.value.copy(task = updatedMaterialTask)
        }
    }

    fun updateMaterialTaskRequests(newRequests: List<MaterialRequest>) {
        if (_taskInfoFlow.value.task is MaterialTask) {
            val materialTask = _taskInfoFlow.value.task as MaterialTask
            val updatedMaterialTask = materialTask.updateMaterialRequests(newRequests)

            // Actualizar el flujo con la nueva tarea
            _taskInfoFlow.value = _taskInfoFlow.value.copy(task = updatedMaterialTask)
        }
    }

    // Método final para realizar el post
    suspend fun postTaskInfo(): AuthRequestResult<Unit> {
        return try {
            val response = api.postTaskInfo(_taskInfoFlow.value)
            if (response.isSuccessful) {
                AuthRequestResult.Authorized(Unit)
            } else {
                AuthRequestResult.Error("Error code: ${response.code}")
            }
        } catch (e: HttpException) {
            val statusCode = e.code()
            AuthRequestResult.Error("Error code: $statusCode")
        } catch (e: Exception) {
            AuthRequestResult.Error("Error: ${e.message ?: " desconocido"}")
        }
    }



}