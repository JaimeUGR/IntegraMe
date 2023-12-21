package com.integrame.app.teacher.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.integrame.app.teacher.domain.repository.TeacherTaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class MakeTaskScreenViewModel @Inject constructor(
    private val teacherTaskRepository: TeacherTaskRepository
) :ViewModel(){
    var startDate: String by mutableStateOf("")
        private set

    var dueDate: String by mutableStateOf("")
        private set

    var tittle: String by mutableStateOf("")
        private set

    var description: String by mutableStateOf("")
        private set

    fun onStartDateChange(startDate: String){
        this.startDate = startDate
    }

    fun onDueDateChange(dueDate: String){
        this.dueDate = dueDate
    }

    fun onTittleChange(tittle: String){
        this.tittle = tittle
    }

    fun onDescriptionChange(description: String){
        this.description = description
    }

    fun createTask(){
        viewModelScope.launch {

        }
    }
}