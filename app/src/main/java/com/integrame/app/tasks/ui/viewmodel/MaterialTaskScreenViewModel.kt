package com.integrame.app.tasks.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.integrame.app.core.data.model.content.ImageContent
import com.integrame.app.tasks.data.model.Material
import com.integrame.app.tasks.data.model.MaterialRequest
import com.integrame.app.tasks.data.model.MaterialTaskModel
import com.integrame.app.tasks.domain.repository.MaterialTaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MaterialTaskScreenViewModel @Inject constructor(
    private val materialTaskRepository: MaterialTaskRepository
): ViewModel() {
    private lateinit var taskModel: MaterialTaskModel

    var materialTaskUIState: MaterialTaskUIState by mutableStateOf(MaterialTaskUIState.Loading)
        private set

    suspend fun loadTaskModel(taskModel: MaterialTaskModel) {
        this.taskModel = taskModel

        viewModelScope.launch {
            // Cargar el material inicial
            val materialRequest = materialTaskRepository.getMaterialRequest(taskModel.taskId, 0)
            materialTaskUIState = MaterialTaskUIState.InRequest(materialRequest, 0)
        }
    }

    fun toggleRequestDelivered() {
        if (materialTaskUIState !is MaterialTaskUIState.InRequest)
            return

        val requestState = (materialTaskUIState as MaterialTaskUIState.InRequest)

        viewModelScope.launch {
            requestState.isDelivered = materialTaskRepository.toggleRequestDelivered(taskModel.taskId, requestState.requestNumber)
        }
    }

    fun nextRequest() {
        if (materialTaskUIState !is MaterialTaskUIState.InRequest)
            return

        val nextRequestNumber = (materialTaskUIState as MaterialTaskUIState.InRequest).requestNumber + 1

        // Es la recompensa
        if (nextRequestNumber >= taskModel.requests) {
            materialTaskUIState = MaterialTaskUIState.InReward
            return
        }

        val nextRequest = materialTaskRepository.getMaterialRequest(taskModel.taskId, nextRequestNumber)
        materialTaskUIState = MaterialTaskUIState.InRequest(nextRequest, nextRequestNumber)
    }

    fun previousRequest() {
        val previousRequestNumber = when (materialTaskUIState) {
            is MaterialTaskUIState.InRequest -> (materialTaskUIState as MaterialTaskUIState.InRequest).requestNumber - 1
            is MaterialTaskUIState.InReward -> { taskModel.requests - 1 }
            is MaterialTaskUIState.Loading -> return
        }

        val previousRequest = materialTaskRepository.getMaterialRequest(taskModel.taskId, previousRequestNumber)
        materialTaskUIState = MaterialTaskUIState.InRequest(previousRequest, previousRequestNumber)
    }
}

sealed interface MaterialTaskUIState {
    object Loading: MaterialTaskUIState
    class InRequest(
        request: MaterialRequest,
        val requestNumber: Int
    ): MaterialTaskUIState {
        val material: Material = request.material
        val displayAmount: ImageContent = request.displayAmount
        var isDelivered by mutableStateOf(request.isDelivered)
    }
    object InReward: MaterialTaskUIState
}
