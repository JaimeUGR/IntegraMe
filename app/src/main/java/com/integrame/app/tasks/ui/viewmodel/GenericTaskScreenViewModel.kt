/*
 *
 * Copyright (c) IntegraMe - Champions 2024 - All rights reserved
 *
 *  You may use, distribute and modify this code under the terms of the
 *  Creative Commons Attribution-NonCommercial 4.0 International (CC BY-NC 4.0) license.
 *
 *  You should have received a copy of the CC BY-NC 4.0 license. Otherwise, read
 *  the full license text here: https://creativecommons.org/licenses/by-nc/4.0/legalcode
 *
 *  This file belongs to the project IntegraMe: https://github.com/JaimeUGR/IntegraMe
 */

package com.integrame.app.tasks.ui.viewmodel

import android.util.Log
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

        Log.i("ASD", "HE LLEGAO")

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
        if (nextStepNumber >= taskModel.steps) {
            genericTaskUIState = GenericTaskUIState.InReward
            return
        }

        genericTaskUIState = GenericTaskUIState.Loading

        viewModelScope.launch {
            val step = genericTaskRepository.getStep(taskModel.taskId, nextStepNumber)
            genericTaskUIState = GenericTaskUIState.InStep(step, nextStepNumber)
        }
    }

    fun previousStep() {
        val previousStepNumber = when (genericTaskUIState) {
            is GenericTaskUIState.InStep -> (genericTaskUIState as GenericTaskUIState.InStep).stepNumber - 1
            is GenericTaskUIState.InReward -> { taskModel.steps - 1 }
            is GenericTaskUIState.Loading -> return
        }

        genericTaskUIState = GenericTaskUIState.Loading

        viewModelScope.launch {
            val step = genericTaskRepository.getStep(taskModel.taskId, previousStepNumber)
            genericTaskUIState = GenericTaskUIState.InStep(step, previousStepNumber)
        }
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
