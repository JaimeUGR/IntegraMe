package com.integrame.app.tasks.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.integrame.app.core.util.AuthRequestResult
import com.integrame.app.tasks.data.model.TaskCard
import com.integrame.app.tasks.domain.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskScheduleScreenViewModel @Inject constructor(
    private val taskRepository: TaskRepository
): ViewModel() {
    var taskScheduleUIState: TaskScheduleUIState by mutableStateOf(TaskScheduleUIState.LoadingTasks)
        private set

    private var taskCards: List<TaskCard> = emptyList()

    var currentPage by mutableIntStateOf(0)
        private set

    init {
        loadPendingTasks()
    }

    fun retryLoadPendingTasks() {
        taskScheduleUIState = TaskScheduleUIState.LoadingTasks
        loadPendingTasks()
    }

    fun getTotalPages(pageSize: Int): Int {
        return (taskCards.size + pageSize - 1) / pageSize
    }

    fun nextPage() {
        currentPage++
    }

    fun previousPage() {
        currentPage--
    }

    fun getTaskCardsPage(pageSize: Int): List<TaskCard> {
        return taskCards.subList(
            currentPage * pageSize,
            minOf((currentPage + 1) * pageSize, taskCards.size)
        )
    }

    // NOTE: Si necesitamos datos del estudiante no se podría hacer directamente así
    private fun loadPendingTasks() {
        viewModelScope.launch {
            taskScheduleUIState = when (val requestResult = taskRepository.getTaskCards()) {
                is AuthRequestResult.Authorized -> {
                    taskCards = requestResult.data
                    TaskScheduleUIState.DisplayTasks
                }
                is AuthRequestResult.Unauthorized -> TaskScheduleUIState.Error("No estás autenticado")
                is AuthRequestResult.Error -> TaskScheduleUIState.Error(requestResult.error)
            }
        }
    }
}

sealed interface TaskScheduleUIState {
    object LoadingTasks: TaskScheduleUIState
    object DisplayTasks: TaskScheduleUIState
    data class Error(
        val error: String
    ): TaskScheduleUIState
}
