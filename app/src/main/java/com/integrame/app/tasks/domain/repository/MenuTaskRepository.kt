package com.integrame.app.tasks.domain.repository

import com.integrame.app.tasks.data.model.ClassroomMenu
import com.integrame.app.tasks.data.model.MenuTaskModel

interface MenuTaskRepository {
    // Para obtener el objeto del modelo de la tarea del menu
    suspend fun getMenuTaskModel(taskId: Int): MenuTaskModel

    // Para sacar la lista de clases que tiene la tarea de menú asociada
    suspend fun getClassroomMenus(taskId: Int): List<ClassroomMenu>

    suspend fun getClassroomMenu(taskId: Int, classroomId: Int): ClassroomMenu

    suspend fun setMenuAmount(taskId: Int, classroomId: Int, menuOptionId: Int, amount: Int)
}
