package com.integrame.app.tasks.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TaskScheduleScreenViewModel @Inject constructor(

): ViewModel() {
    var taskScheduleUIState: TaskScheduleUIState by mutableStateOf(TaskScheduleUIState.LoadingTasks)
        private set

    var currentPage by mutableIntStateOf(0)
        private set

    init {

    }
}

sealed interface TaskScheduleUIState {
    object LoadingTasks: TaskScheduleUIState
    object TasksLoaded: TaskScheduleUIState
}
