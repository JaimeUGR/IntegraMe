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

package com.integrame.app.teacher.data.model.task

import com.integrame.app.core.data.model.content.RemoteImage
import kotlinx.serialization.Serializable

@Serializable
data class MenuTask(
    override var displayName: String,
    override var displayImage: RemoteImage,
    val classroomMenus: List<ClassroomMenuTask>
): Task() {
    // Método para actualizar ClassroomMenuTask
    fun updateClassroomMenu(classroomId: Int, newMenuOptions: List<MenuOption>): MenuTask {
        val updatedClassroomMenus = classroomMenus.map {
            if (it.classroomId == classroomId) {
                ClassroomMenuTask(classroomId, newMenuOptions)
            } else {
                it
            }
        }

        return copy(classroomMenus = updatedClassroomMenus)
    }
}

@Serializable
data class ClassroomMenuTask(
    val classroomId: Int,
    val menuOptions: List<MenuOption>
)

@Serializable
data class MenuOption(
    val name: String,
    val image: RemoteImage
)
