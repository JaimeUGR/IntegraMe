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

package com.integrame.app.tasks.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.integrame.app.tasks.data.model.ClassroomMenu
import com.integrame.app.tasks.data.model.MenuTaskModel
import com.integrame.app.tasks.domain.repository.MenuTaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuTaskScreenViewModel @Inject constructor(
    private val menuTaskRepository: MenuTaskRepository
): ViewModel() {
    private lateinit var taskModel: MenuTaskModel
    var uiStateClassroomList: MenuTaskUIState by mutableStateOf(MenuTaskUIState.Loading)
        private set

    suspend fun loadTaskModel(menuTaskModel: MenuTaskModel) {
        uiStateClassroomList = MenuTaskUIState.Loading
        taskModel = menuTaskModel
        displayClassroomList()
    }

    fun displayClassroomList() {
        uiStateClassroomList = MenuTaskUIState.SelectClassroom
    }

    fun selectClassroom(classroomId: Int) {
        uiStateClassroomList = MenuTaskUIState.Loading

        viewModelScope.launch {
            // TODO: control de erorres
            val classroomMenu = menuTaskRepository.getClassroomMenu(taskId = taskModel.taskId, classroomId = classroomId)
            uiStateClassroomList = MenuTaskUIState.SelectMenus(classroomMenu)
        }
    }

    fun updateMenuAmount(menuOptionIndex: Int, amount: Int) {
        if (uiStateClassroomList !is MenuTaskUIState.SelectMenus)
            return

        val selectMenusUIState = (uiStateClassroomList as MenuTaskUIState.SelectMenus)

        viewModelScope.launch {
            val menuOption = selectMenusUIState.classroomMenu.menuOptions[menuOptionIndex]

            menuTaskRepository.setMenuAmount(
                taskId = taskModel.taskId,
                classroomId = selectMenusUIState.classroomMenu.classroom.classroomId,
                menuOptionId = menuOption.menuOptionId,
                amount = amount
            )

            selectMenusUIState.setMenuOptionAmount(menuOptionIndex, amount)
        }
    }
}

sealed interface MenuTaskUIState {
    object Loading: MenuTaskUIState
    object SelectClassroom: MenuTaskUIState
    data class SelectMenus(val classroomMenu: ClassroomMenu): MenuTaskUIState {
        var selectedMenuIndex by mutableIntStateOf(0)
            private set
        var selectedMenuAmount by mutableIntStateOf(classroomMenu.menuOptions[0].requestedAmount)
            private set

        fun setMenuOptionAmount(menuIndex: Int, amount: Int) {
            if (selectedMenuIndex == menuIndex)
                selectedMenuAmount = amount

            classroomMenu.menuOptions[menuIndex].requestedAmount = amount
        }

        fun nextMenuOption() {
            selectedMenuIndex++
            selectedMenuAmount = classroomMenu.menuOptions[selectedMenuIndex].requestedAmount
        }

        fun previousMenuOption() {
            selectedMenuIndex--
            selectedMenuAmount = classroomMenu.menuOptions[selectedMenuIndex].requestedAmount
        }
    }
}
