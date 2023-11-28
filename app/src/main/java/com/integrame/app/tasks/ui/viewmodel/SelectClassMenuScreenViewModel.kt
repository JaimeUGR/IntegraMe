package com.integrame.app.tasks.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.integrame.app.tasks.data.model.ClassroomMenuTask
import com.integrame.app.tasks.data.model.MenuTaskModel
import com.integrame.app.tasks.domain.repository.MenuTaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectClassMenuViewModel @Inject constructor(
    private val menuTaskRepository: MenuTaskRepository
): ViewModel() {
    private lateinit var menuTaskModel: MenuTaskModel
    var uiState: SelectClassMenuUIState by mutableStateOf(SelectClassMenuUIState.Loading)
        private set

    var classrooms: List<Int> = emptyList()
        private set

    suspend fun loadClassrooms(menuTaskModel: MenuTaskModel) {
        this.menuTaskModel = menuTaskModel
        uiState = SelectClassMenuUIState.Loading

        viewModelScope.launch {
            // Cargar la lista de aulas
            classrooms = menuTaskRepository.getClassroomIds(menuTaskModel.taskId)
            // Cambiar el estado a ListLoaded cuando se cargan las aulas
            uiState = SelectClassMenuUIState.ListLoaded(classrooms)
        }
    }

    /*
    fun selectClassroom(classroom: ClassroomMenuTask) {
        _selectedClassroom.value = classroom
    }
     */

}

sealed interface SelectClassMenuUIState {
    object Loading : SelectClassMenuUIState
    class ListLoaded(val classrooms: List<Int>?) : SelectClassMenuUIState

}