package com.integrame.app.tasks.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.integrame.app.tasks.data.model.MenuOption
import com.integrame.app.tasks.data.model.MenuTaskModel
import com.integrame.app.tasks.domain.repository.MenuTaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuTaskScreenViewModel @Inject constructor(
    private val menuTaskRepository: MenuTaskRepository
): ViewModel() {
    private lateinit var menuTaskModel: MenuTaskModel
    var uiStateClassroomList: ClassroomListUIState by mutableStateOf(ClassroomListUIState.Loading)
        private set

    var uiStateSelectMenu: SelectMenuUIState by mutableStateOf(SelectMenuUIState.Loading)
        private set

    var classroomMenus: List<MenuOption> = emptyList()
        private set

    var selectClassroom: Int = 0

    var rememberAmount: Int by mutableIntStateOf(0)

    var numMenu: Int = 0

    init{

    }

    fun updateSelectClassroom(idClassroom: Int){
        selectClassroom = idClassroom
    }

    fun updateRememberAmount(amount: Int){
        rememberAmount = amount
    }

    fun updateRequestedAmount(numMenu: Int, amount: Int){
        var menuSelect = classroomMenus[numMenu]

        menuSelect.setRequestAmount(amount)

    }


    suspend fun loadClassroomsIds(menuTaskModel: MenuTaskModel) {
        this.menuTaskModel = menuTaskModel
        uiStateClassroomList = ClassroomListUIState.Loading

        viewModelScope.launch {
            // Cargar la lista de aulas
            val classroomsIds = menuTaskRepository.getClassroomIds(menuTaskModel.taskId)
            // Cambiar el estado a ListLoaded cuando se cargan las aulas
            uiStateClassroomList = ClassroomListUIState.ListLoaded(classroomsIds)
        }
    }


    suspend fun loadClassroomsMenus(menuTaskModel: MenuTaskModel) {
        this.menuTaskModel = menuTaskModel
        uiStateSelectMenu = SelectMenuUIState.Loading

        viewModelScope.launch {
            // Cargar la lista de aulas
            val classroomMenus = menuTaskRepository.getMenuOptions(menuTaskModel.taskId, selectClassroom )
            // Cambiar el estado a ListLoaded cuando se cargan las aulas
            uiStateSelectMenu = SelectMenuUIState.ListLoaded(classroomMenus)
        }
    }



}

sealed interface ClassroomListUIState {
    object Loading : ClassroomListUIState
    data class ListLoaded(val classroomsIds: List<Int>) : ClassroomListUIState


}

sealed interface SelectMenuUIState{
    object Loading : SelectMenuUIState
    data class ListLoaded(val classroomMenus: List<MenuOption>) : SelectMenuUIState

}