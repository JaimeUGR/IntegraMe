package com.integrame.app.tasks.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.integrame.app.core.util.AuthRequestResult
import com.integrame.app.tasks.data.model.Task
import com.integrame.app.tasks.domain.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskScreenViewModel @Inject constructor(
    private val taskRepository: TaskRepository
): ViewModel() {
    var taskScreenUIState: TaskScreenUIState by mutableStateOf(TaskScreenUIState.Loading)
        private set

    fun loadTask(taskId: Int) {
        viewModelScope.launch {
            taskScreenUIState = TaskScreenUIState.Loading
            taskScreenUIState = when (val requestResult = taskRepository.getTask(taskId)) {
                is AuthRequestResult.Authorized -> TaskScreenUIState.TaskReady(requestResult.data)
                is AuthRequestResult.Unauthorized -> TaskScreenUIState.Unauthorized
                is AuthRequestResult.Error -> TaskScreenUIState.Error(requestResult.error)
            }
        }
    }
}

sealed interface TaskScreenUIState {
    object Loading: TaskScreenUIState
    data class TaskReady(val task: Task): TaskScreenUIState
    object Unauthorized: TaskScreenUIState
    data class Error(val error: String): TaskScreenUIState
}
