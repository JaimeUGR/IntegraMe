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

package com.integrame.app.teacher.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.integrame.app.teacher.data.model.task.TaskCard
import com.integrame.app.teacher.data.model.task.TaskInfo
import com.integrame.app.teacher.domain.repository.TeacherTaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectTaskModelScreenViewModel @Inject constructor(
    private val teacherTaskRepository: TeacherTaskRepository
): ViewModel(){
    var uiStateTasKModelList: ListTaskModelUIState by mutableStateOf(ListTaskModelUIState.Loading)
        private set

    var uiStateTaskModelListIds: ListTaskModelUIState by mutableStateOf(ListTaskModelUIState.Loading)
        private set

    var userId by mutableIntStateOf(0)

    var selectTaskModel: Int = 0

    var tittle: String by mutableStateOf("")
        private set

    var description: String by mutableStateOf("")
        private set

    var startDate: String by mutableStateOf("")
        private set

    var dueDate: String by mutableStateOf("")
        private set

    var reward: Int by mutableIntStateOf(0)
        private set


    fun onTittleChange(tittle: String) {
        this.tittle = tittle
    }

    fun onDescriptionChange(description: String) {
        this.description = description
    }

    fun onStartDateChange(startDate: String) {
        this.startDate = startDate
    }

    fun onDueDateChange(dueDate: String) {
        this.dueDate = dueDate
    }

    fun onRewardChange(reward: Int) {
        this.reward = reward
    }

    fun updateSelectTaskModel(idTaskModel: Int){
        selectTaskModel = idTaskModel
    }

    suspend fun loadTaskModels() {
        uiStateTasKModelList = ListTaskModelUIState.Loading

        viewModelScope.launch {
            // Cargar los taskModel
            val taskCardList = teacherTaskRepository.getListTaskCard()

            uiStateTasKModelList = ListTaskModelUIState.ListTaskModelReady(taskCardList)

            // Obtener los ids de los TaskCard
            val taskCardIdsList = taskCardList.map { it.taskId }

            uiStateTaskModelListIds = ListTaskModelUIState.ListTaskModelIdsReady(taskCardIdsList)

        }

    }

}

sealed interface ListTaskModelUIState {
    object Loading : ListTaskModelUIState
    data class ListTaskModelIdsReady(
        val listTaskModelIds: List<Int>
    ) : ListTaskModelUIState

    data class ListTaskModelReady(
        val listTaskModel: List<TaskCard>
    ) : ListTaskModelUIState


    data class Error(
        val error: String
    ) : ListTaskModelUIState
}