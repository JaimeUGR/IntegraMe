package com.integrame.app.teacher.ui.viewmodel

import android.icu.util.CurrencyAmount
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.integrame.app.core.data.fake.FakeResources
import com.integrame.app.core.data.model.content.ContentPack
import com.integrame.app.teacher.data.model.task.GenericTaskStep
import com.integrame.app.teacher.data.model.task.MaterialRequest
import com.integrame.app.teacher.domain.repository.TeacherTaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MakeTaskScreenViewModel @Inject constructor(
    private val teacherTaskRepository: TeacherTaskRepository
) :ViewModel(){

    var makeTaskUIState: MakeTaskUIState by mutableStateOf(MakeTaskUIState.Loading)
        private set

    var genericStepList: List<GenericTaskStep> by mutableStateOf(emptyList())
        private set

    var userId:Int by mutableIntStateOf(0)
        private set

    var genericTaskStartDate: String by mutableStateOf("")
        private set

    var genericTaskDueDate: String by mutableStateOf("")
        private set

    var genericTaskTittle: String by mutableStateOf("")
        private set

    var genericTaskDescription: String by mutableStateOf("")
        private set

    var genericTaskStepDescription: String by mutableStateOf("")
        private set

    var genericTaskStepTittle: String by mutableStateOf("")
        private set

    var materialTaskTittle: String by mutableStateOf("")
        private set

    var materialTaskDescription: String by mutableStateOf("")
        private set

    var materialTaskstartDate: String by mutableStateOf("")
        private set

    var materialTaskDueDate: String by mutableStateOf("")
        private set

    var materialsList: List<MaterialRequest> by mutableStateOf(emptyList())
        private set

    var materialName: String by mutableStateOf("")
        private set

    var materialAmount: Int by mutableIntStateOf(0)
        private set

    var materialPropertyName: String by mutableStateOf("")
        private set

    init {
        this.genericStepList.toMutableList()
        this.materialsList.toMutableList()
    }

    fun GenericStartDateChange(startDate: String){
        this.genericTaskStartDate = startDate
    }

    fun onGenericDueDateChange(dueDate: String){
        this.genericTaskDueDate = dueDate
    }

    fun onGenericTittleChange(tittle: String){
        this.genericTaskTittle = tittle
    }

    fun onGenericDescriptionChange(description: String){
        this.genericTaskDescription = description
    }

    fun setStudentId(userId: Int) {
        this.userId = userId
    }

    fun onGenericDescriptionStepChange(description: String) {
        this.genericTaskStepDescription = description
    }

    fun onGenericContentPackChange(contentPack: ContentPack) {


    }

    fun onGenericStepTittleChange(tittle: String) {
        this.genericTaskStepTittle = tittle
    }

    fun onMaterialNameChange(name: String) {
        this.materialName = name
    }

    fun onMaterialAmountChange(amount: Int) {
        this.materialAmount = amount
    }

    fun onMaterialPropertyNameChange(name: String) {
        this.materialPropertyName = name
    }


    fun addGenericStep(contentPack: ContentPack) {
        val newStep = GenericTaskStep(this.genericTaskStepTittle, this.genericTaskStepDescription, contentPack)

        this.genericStepList += newStep

    }

    fun createGenericTask() {
        val description = this.genericTaskDescription
        val genericTaskImage = FakeResources.remoteImages[0]
        val genericTaskSteps = this.genericStepList

        viewModelScope.launch {
            teacherTaskRepository.updateGenericTask(description, genericTaskImage)
            teacherTaskRepository.updateGenericTaskSteps(genericTaskSteps)


        }
    }

    fun createMaterialTask() {


        viewModelScope.launch {

        }

    }
}

class EditContentPack {
    var text by mutableStateOf("")
    var imageId by mutableIntStateOf(0)
    var videoId by mutableIntStateOf(0)
    var audioId by mutableIntStateOf(0)


}

sealed interface MakeTaskUIState {
    object Loading : MakeTaskUIState
    data class Error(
        val error: String
    ) : MakeTaskUIState
}



