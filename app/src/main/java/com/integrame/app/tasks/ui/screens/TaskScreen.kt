package com.integrame.app.tasks.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.integrame.app.core.data.fake.FakeResources
import com.integrame.app.core.data.network.toContentProfile
import com.integrame.app.core.ui.components.ErrorCard
import com.integrame.app.tasks.data.model.GenericTaskModel
import com.integrame.app.tasks.data.model.MaterialTaskModel
import com.integrame.app.tasks.data.model.MenuTaskModel
import com.integrame.app.tasks.ui.viewmodel.TaskScreenUIState
import com.integrame.app.tasks.ui.viewmodel.TaskScreenViewModel

@Composable
fun TaskScreen(
    taskId: Int,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    taskScreenViewModel: TaskScreenViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        taskScreenViewModel.loadTask(taskId)
    }

    when(val taskScreenUIState = taskScreenViewModel.taskScreenUIState) {
        is TaskScreenUIState.Loading -> {
            Box(modifier = modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier
                    .align(Alignment.Center)
                    .size(80.dp))
            }
        }
        is TaskScreenUIState.Unauthorized -> {
            Box(modifier = modifier.fillMaxSize()) {
                ErrorCard(
                    errorDescription = "No estás autorizado para ver esta actividad",
                    errorButtonText = "Retroceder",
                    errorButtonImage = {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Retroceder",
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    },
                    onPressContinue = onNavigateBack,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        is TaskScreenUIState.Error -> {
            Box(modifier = modifier.fillMaxSize()) {
                ErrorCard(
                    errorDescription = taskScreenUIState.error,
                    errorButtonText = "Cerrar",
                    errorButtonImage = {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "Cerrar",
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    },
                    onPressContinue = { taskScreenViewModel.loadTask(taskId) },
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        is TaskScreenUIState.TaskReady -> {
            when (val taskModel = taskScreenUIState.taskModel) {
                is GenericTaskModel -> {
                    GenericTaskScreen(
                        taskModel = taskModel,
                        contentProfile = FakeResources.contentProfiles[0].toContentProfile(),
                        onNavigateBack = onNavigateBack,
                        onPressHome = { /*TODO*/ },
                        onPressChat = { /*TODO*/ },
                        modifier = Modifier.fillMaxSize()
                    )
                }
                is MenuTaskModel -> {
                    Text(text = "Menu Task")
                }
                is MaterialTaskModel -> {
                    MaterialTaskScreen(
                        taskModel = taskModel,
                        contentProfile = FakeResources.contentProfiles[0].toContentProfile(),
                        onNavigateBack = onNavigateBack,
                        onPressHome = { /*TODO*/ },
                        onPressChat = { /*TODO*/ },
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}
