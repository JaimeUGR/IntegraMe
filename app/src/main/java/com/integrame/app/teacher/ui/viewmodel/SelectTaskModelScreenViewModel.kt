package com.integrame.app.teacher.ui.viewmodel

import androidx.compose.runtime.getValue
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

    var userId: Int by mutableStateOf(0)

    var selectTaskModel: Int = 0

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