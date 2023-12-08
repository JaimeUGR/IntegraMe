package com.integrame.app.teacher.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.integrame.app.core.util.RequestResult
import com.integrame.app.login.data.model.IdentityCard
import com.integrame.app.teacher.data.model.task.TaskInfo
import com.integrame.app.teacher.domain.repository.TeacherTaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.integrame.app.login.ui.viewmodel.StudentLoginUIState
import kotlinx.coroutines.launch

@HiltViewModel
class AsignTaskScreenViewModel @Inject constructor(
    private val teacherTaskRepository: TeacherTaskRepository
): ViewModel(){
    private lateinit var taskInfo: TaskInfo

    var selectStudentUIState: SelectStudentUIState by mutableStateOf(SelectStudentUIState.Loading)
        private set

    var tasKModelList: ListTaskModelUIState by mutableStateOf(ListTaskModelUIState.Loading)
        private set

    init {
        viewModelScope.launch {
            loadIdentityCards()
        }
    }

    fun getIdentityCardsPage(): List<IdentityCard> {
        if (selectStudentUIState !is SelectStudentUIState.IdentitiesReady)
            return emptyList()

        val identityCards = (selectStudentUIState as SelectStudentUIState.IdentitiesReady).identityCards

        return identityCards

    }

    fun reloadIdentityCards() {
        if (selectStudentUIState == SelectStudentUIState.Loading)
            return

        viewModelScope.launch {
            loadIdentityCards()
        }
    }

    private suspend fun loadIdentityCards(){
        selectStudentUIState = SelectStudentUIState.Loading
        selectStudentUIState = when (val requestResult = teacherTaskRepository.uploadStudentsCards()){
            is RequestResult.Success -> SelectStudentUIState.IdentitiesReady(requestResult.data)
            is RequestResult.Error -> SelectStudentUIState.Error(requestResult.error)
        }
    }

    suspend fun loadTaskModels(){

    }

}

sealed interface SelectStudentUIState {
    object Loading : SelectStudentUIState
    data class IdentitiesReady(
        val identityCards: List<IdentityCard>
    ) : SelectStudentUIState
    data class Error(
        val error: String
    ) : SelectStudentUIState
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