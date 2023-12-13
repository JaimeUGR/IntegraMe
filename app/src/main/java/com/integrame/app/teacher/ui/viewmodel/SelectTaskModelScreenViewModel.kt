package com.integrame.app.teacher.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.integrame.app.teacher.data.model.task.TaskInfo
import com.integrame.app.teacher.domain.repository.TeacherTaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectTaskModelScreenViewModel @Inject constructor(
    private val teacherTaskRepository: TeacherTaskRepository
): ViewModel(){
    private lateinit var taskInfo: TaskInfo

    var tasKModelList: ListTaskModelUIState by mutableStateOf(ListTaskModelUIState.Loading)
        private set

    suspend fun loadTaskModels(){

    }

}

sealed interface ListTaskModelUIState {
    object Loading : ListTaskModelUIState
    data class ListTaskModelReady(
        val listTaskModel: List<TaskInfo>
    ) : ListTaskModelUIState
    data class Error(
        val error: String
    ) : ListTaskModelUIState
}