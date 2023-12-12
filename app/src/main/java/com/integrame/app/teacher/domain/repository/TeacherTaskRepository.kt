package com.integrame.app.teacher.domain.repository

import com.integrame.app.core.data.model.content.ImageContent
import com.integrame.app.core.data.model.content.RemoteImage
import com.integrame.app.core.util.AuthRequestResult
import com.integrame.app.core.util.RequestResult
import com.integrame.app.login.data.model.IdentityCard
import com.integrame.app.teacher.data.model.task.GenericTaskStep
import com.integrame.app.teacher.data.model.task.MaterialRequest
import com.integrame.app.teacher.data.model.task.MenuOption
import com.integrame.app.teacher.data.model.task.Task
import com.integrame.app.teacher.data.model.task.TaskInfo
import com.integrame.app.teacher.data.model.task.TaskType

interface TeacherTaskRepository {
    fun setAssignedStudentId(assignedStundentId: Int)

    fun setAssignedTeachersIds(assignedTeachersIds: List<Int>)

    fun setDescription(description: String)

    fun setStartDate(startDate: Long)

    fun setDueDate(dueDate: Long)

    fun setTaskType(taskType: TaskType)

    fun setReward(reward: Int)

    fun setTask(task: Task)

    fun updateMenuTask(displayName: String, displayImage: RemoteImage)

    fun updateClassroomMenuTask(classroomId: Int, newMenuOptions: List<MenuOption>)

    fun updateGenericTask(displayName: String, displayImage: ImageContent)

    fun updateGenericTaskSteps(newSteps: List<GenericTaskStep>)

    fun updateMaterialTask(displayName: String, displayImage: ImageContent)

    fun updateMaterialTaskRequests(newRequests: List<MaterialRequest>)

    suspend fun postTaskInfo(taskInfo: TaskInfo)

    suspend fun uploadStudentsCards(): RequestResult<List<IdentityCard>>



}