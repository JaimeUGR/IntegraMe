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
