package com.integrame.app.tasks.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.integrame.app.core.data.fake.FakeResources
import com.integrame.app.core.data.model.content.ContentPack
import com.integrame.app.tasks.data.model.GenericTask
import com.integrame.app.tasks.data.model.GenericTaskModel
import com.integrame.app.tasks.data.model.GenericTaskStep
import com.integrame.app.tasks.domain.repository.GenericTaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenericTaskScreenViewModel @Inject constructor(
    private val genericTaskRepository: GenericTaskRepository
): ViewModel() {
    private lateinit var taskModel: GenericTaskModel

    var genericTaskUIState: GenericTaskUIState by mutableStateOf(GenericTaskUIState.Loading)
        private set

    suspend fun loadTaskModel(taskModel: GenericTaskModel) {
        this.taskModel = taskModel

        viewModelScope.launch {
            // Cargar el paso inicial
            val step = genericTaskRepository.getStep(taskModel.taskId, 0)
            genericTaskUIState = GenericTaskUIState.InStep(step, 0)
        }
    }

    fun toggleStepCompleted() {
        if (genericTaskUIState !is GenericTaskUIState.InStep)
            return

        val stepState = (genericTaskUIState as GenericTaskUIState.InStep)

        viewModelScope.launch {
            stepState.isCompleted = genericTaskRepository.toggleStepCompleted(taskModel.taskId, stepState.stepNumber)
        }
    }

    fun nextStep() {
        if (genericTaskUIState !is GenericTaskUIState.InStep)
            return

        val nextStepNumber = (genericTaskUIState as GenericTaskUIState.InStep).stepNumber + 1

        // Es la recompensa
        if (nextStepNumber == taskModel.steps) {
            genericTaskUIState = GenericTaskUIState.InReward
            return
        }

        val step = genericTaskRepository.getStep(taskModel.taskId, nextStepNumber)
        genericTaskUIState = GenericTaskUIState.InStep(step, nextStepNumber)
    }

    fun previousStep() {
        val previousStepNumber = when (genericTaskUIState) {
            is GenericTaskUIState.InStep -> (genericTaskUIState as GenericTaskUIState.InStep).stepNumber - 1
            is GenericTaskUIState.InReward -> { taskModel.steps - 1 }
            is GenericTaskUIState.Loading -> return
        }

        val step = genericTaskRepository.getStep(taskModel.taskId, previousStepNumber)
        genericTaskUIState = GenericTaskUIState.InStep(step, previousStepNumber)
    }
}

sealed interface GenericTaskUIState {
    object Loading: GenericTaskUIState
    class InStep(
        step: GenericTaskStep,
        val stepNumber: Int
    ): GenericTaskUIState {
        val stepContent: ContentPack = step.content
        var isCompleted by mutableStateOf(step.isCompleted)
    }
    object InReward: GenericTaskUIState
}