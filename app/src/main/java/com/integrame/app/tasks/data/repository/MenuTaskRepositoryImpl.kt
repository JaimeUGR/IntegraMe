package com.integrame.app.tasks.data.repository

import com.integrame.app.core.data.network.api.IntegraMeApi
import com.integrame.app.tasks.data.model.ClassroomMenu
import com.integrame.app.tasks.data.model.MenuTaskModel
import com.integrame.app.tasks.data.network.NetworkPostMenuOptionAmount
import com.integrame.app.tasks.domain.repository.MenuTaskRepository

class MenuTaskRepositoryImpl(
    private val api: IntegraMeApi
): MenuTaskRepository {
    override suspend fun getMenuTaskModel(taskId: Int): MenuTaskModel {
        return api.getMenuTaskModel(taskId)
    }

    override suspend fun getClassroomMenus(taskId: Int): List<ClassroomMenu> {
        return api.getMenuTaskClassroomMenus(taskId)
    }

    override suspend fun getClassroomMenu(taskId: Int, classroomId: Int): ClassroomMenu {
        return api.getMenuTaskClassroomMenu(taskId, classroomId)
    }

    override suspend fun setMenuAmount(taskId: Int, classroomId: Int, menuOptionId: Int, amount: Int) {
        api.setClassroomMenuOptionAmount(taskId, classroomId, menuOptionId, NetworkPostMenuOptionAmount(amount))
    }
}
