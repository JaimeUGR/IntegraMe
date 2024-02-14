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

package com.integrame.app.teacher.data.repository

import com.integrame.app.core.data.fake.FakeResources
import com.integrame.app.core.data.model.content.ImageContent
import com.integrame.app.core.data.model.content.RemoteImage
import com.integrame.app.core.data.network.api.IntegraMeApi
import com.integrame.app.core.util.AuthRequestResult
import com.integrame.app.core.util.RequestResult
import com.integrame.app.login.data.model.IdentityCard
import com.integrame.app.login.data.repository.IdentityCardRepositoryImpl
import com.integrame.app.tasks.data.model.TaskModel
import com.integrame.app.teacher.data.model.task.Task
import com.integrame.app.teacher.data.model.task.GenericTask
import com.integrame.app.teacher.data.model.task.GenericTaskStep
import com.integrame.app.teacher.data.model.task.MaterialRequest
import com.integrame.app.teacher.data.model.task.MaterialTask
import com.integrame.app.teacher.data.model.task.MenuOption
import com.integrame.app.teacher.data.model.task.MenuTask
import com.integrame.app.teacher.data.model.task.TaskCard
import com.integrame.app.teacher.data.model.task.TaskInfo
import com.integrame.app.teacher.data.model.task.TaskType
import com.integrame.app.teacher.domain.repository.TeacherTaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.HttpException

// TODO: LifeCycle de ViewModel para Dagger
class TeacherTaskRepositoryImpl (
    private val api: IntegraMeApi,
    private val identityCardRepositoryImpl: IdentityCardRepositoryImpl
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
            startDate = "",
            dueDate = "",
            task = null,
            taskType = null,
            reward = null,
        )
    }

    init {
        // Inicializar flujo
        _taskInfoFlow.value = getDefaultTaskInfo()
    }

    // Métodos set para cada atributo
    override fun setAssignedStudentId(assignedStudentId: Int) {
        _taskInfoFlow.value = _taskInfoFlow.value.copy(assignedStudentId = assignedStudentId)
    }

    override fun setAssignedTeachersIds(assignedTeachersIds: List<Int>) {
        _taskInfoFlow.value = _taskInfoFlow.value.copy(assignedTeachersIds = assignedTeachersIds)
    }

    override fun setDescription(description: String) {
        _taskInfoFlow.value = _taskInfoFlow.value.copy(description = description)
    }

    override fun setStartDate(startDate: String) {
        _taskInfoFlow.value = _taskInfoFlow.value.copy(startDate = startDate)
    }

    override fun setDueDate(dueDate: String) {
        _taskInfoFlow.value = _taskInfoFlow.value.copy(dueDate = dueDate)
    }
    override fun setTaskType(taskType: TaskType) {
        _taskInfoFlow.value = _taskInfoFlow.value.copy(taskType = taskType)
    }

    override fun setReward(reward: Int) {
        _taskInfoFlow.value = _taskInfoFlow.value.copy(reward = reward)
    }

    override fun setTask(task: Task) {
        _taskInfoFlow.value = _taskInfoFlow.value.copy(task = task)
    }

    // Método en el repositorio para actualizar la información de la tarea de Menú
    override fun updateMenuTask(displayName: String, displayImage: RemoteImage) {
        if (_taskInfoFlow.value.task is MenuTask) {
            val menuTask = _taskInfoFlow.value.task as MenuTask
            menuTask.displayName = displayName
            menuTask.displayImage = displayImage

            // Actualizar el flujo con la nueva tarea
            _taskInfoFlow.value = _taskInfoFlow.value.copy(task = menuTask)
        }
    }

    override fun updateClassroomMenuTask(classroomId: Int, newMenuOptions: List<MenuOption>) {
        if (_taskInfoFlow.value.task is MenuTask) {
            val menuTask = _taskInfoFlow.value.task as MenuTask
            val updatedMenuTask = menuTask.updateClassroomMenu(classroomId, newMenuOptions)

            // Actualizar el flujo con la nueva tarea
            _taskInfoFlow.value = _taskInfoFlow.value.copy(task = updatedMenuTask)
        }
    }

    // Método en el repositorio para actualizar la información de la tarea genérica
    override fun updateGenericTask(displayName: String, displayImage: RemoteImage) {
        if (_taskInfoFlow.value.task is GenericTask) {
            val genericTask = _taskInfoFlow.value.task as GenericTask
            val updatedGenericTask = genericTask
                .setDisplayName(displayName)
                .setDisplayImage(displayImage)

            // Actualizar el flujo con la nueva tarea
            _taskInfoFlow.value = _taskInfoFlow.value.copy(task = updatedGenericTask)
        }
    }

    override fun updateGenericTaskSteps(newSteps: List<GenericTaskStep>) {
        if (_taskInfoFlow.value.task is GenericTask) {
            val genericTask = _taskInfoFlow.value.task as GenericTask
            val updatedGenericTask = genericTask.updateSteps(newSteps)

            // Actualizar el flujo con la nueva tarea
            _taskInfoFlow.value = _taskInfoFlow.value.copy(task = updatedGenericTask)
        }
    }

    // Método en el repositorio para actualizar la información de la tarea de material
    override fun updateMaterialTask(displayName: String, displayImage: ImageContent) {
        if (_taskInfoFlow.value.task is MaterialTask) {
            val materialTask = _taskInfoFlow.value.task as MaterialTask
            val updatedMaterialTask = materialTask
                .setDisplayName(displayName)
                .setDisplayImage(displayImage)

            // Actualizar el flujo con la nueva tarea
            _taskInfoFlow.value = _taskInfoFlow.value.copy(task = updatedMaterialTask)
        }
    }

    override fun updateMaterialTaskRequests(newRequests: List<MaterialRequest>) {
        if (_taskInfoFlow.value.task is MaterialTask) {
            val materialTask = _taskInfoFlow.value.task as MaterialTask
            val updatedMaterialTask = materialTask.updateMaterialRequests(newRequests)

            // Actualizar el flujo con la nueva tarea
            _taskInfoFlow.value = _taskInfoFlow.value.copy(task = updatedMaterialTask)
        }
    }

    // Método final para realizar el post
    override suspend fun postTaskInfo(taskInfo: TaskInfo) {
        api.postTaskInfo(_taskInfoFlow.value)
    }

    override suspend fun uploadStudentsCards(): RequestResult<List<IdentityCard>> {
        return identityCardRepositoryImpl.getStudentsIdentityCards()
    }

    override suspend fun getListTaskCard(): List<TaskCard> {
        TODO("NOT YET IMPLEMENTED")
    }
}
