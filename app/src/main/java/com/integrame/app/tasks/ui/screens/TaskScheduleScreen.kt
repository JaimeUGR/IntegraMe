package com.integrame.app.tasks.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.EventNote
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.integrame.app.core.data.fake.FakeResources
import com.integrame.app.core.data.model.content.RemoteImage
import com.integrame.app.core.data.model.user.StudentProfile
import com.integrame.app.core.ui.components.DynamicImage
import com.integrame.app.core.ui.components.ErrorCard
import com.integrame.app.core.ui.components.appbar.PaginatedBottomAppBar
import com.integrame.app.tasks.data.model.TaskCard
import com.integrame.app.tasks.ui.components.TaskCard
import com.integrame.app.tasks.ui.viewmodel.TaskScheduleScreenViewModel
import com.integrame.app.tasks.ui.viewmodel.TaskScheduleUIState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScheduleScreen(
    studentProfile: StudentProfile,
    onSignOut: () -> Unit,
    onPressNotifications: () -> Unit,
    onPressProfile: () -> Unit,
    onSelectTask: (Int) -> Unit,
    modifier: Modifier = Modifier,
    pageSize: Int = 4,
    taskScheduleScreenViewModel: TaskScheduleScreenViewModel = hiltViewModel()
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Actividades",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium)
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                navigationIcon = {
                    IconButton(onClick = onSignOut) {
                        Icon(
                            imageVector = Icons.Filled.Logout,
                            contentDescription = "Cerrar sesión",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onPressNotifications) {
                        Icon(
                            imageVector = Icons.Filled.Notifications,
                            contentDescription = "Avisos y notificaciones",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    IconButton(onClick = onPressProfile) {
                        DynamicImage(
                            image = studentProfile.avatar,
                            modifier = Modifier
                                .clip(CircleShape)
                                .semantics {
                                    contentDescription = "Perfil"
                                },
                            contentScale = ContentScale.Crop
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        },
        bottomBar = {
            val currentPage = taskScheduleScreenViewModel.currentPage
            val lastPage = taskScheduleScreenViewModel.getTotalPages(pageSize) - 1

            PaginatedBottomAppBar(
                currentPage = currentPage + 1,
                isFirstPage = currentPage == 0,
                isLastPage = currentPage == lastPage,
                onPressNext = { taskScheduleScreenViewModel.nextPage() },
                onPressPrevious = { taskScheduleScreenViewModel.previousPage() },
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        }
    ) { innerPadding ->
        when (val taskScheduleUIState = taskScheduleScreenViewModel.taskScheduleUIState) {
            is TaskScheduleUIState.LoadingTasks -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                )
            }
            is TaskScheduleUIState.Error -> {
                ErrorCard(
                    errorDescription = taskScheduleUIState.error,
                    errorButtonText = "Reintentar",
                    errorButtonImage = { /*TODO*/ },
                    onPressContinue = { taskScheduleScreenViewModel.retryLoadPendingTasks() }
                )
            }
            is TaskScheduleUIState.DisplayTasks -> {
                TaskCardList(
                    cards = taskScheduleScreenViewModel.getTaskCardsPage(pageSize),
                    onSelectTask = onSelectTask,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}

@Composable
private fun TaskCardList(
    cards: List<TaskCard>,
    onSelectTask: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.semantics { testTag = "TaskCard" },
        userScrollEnabled = false
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                AsyncImage(
                    model = "https://static.arasaac.org/pictograms/5898/5898_300.png",
                    contentDescription = "Imagen Agenda",
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.tertiaryContainer,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = 16.dp)
                        .requiredHeight(128.dp)
                )
            }
        }

        items(
            items = cards,
            key = { taskCard -> taskCard.taskId }
        ) { taskCard ->
            TaskCard(
                taskCard = taskCard,
                onCardClick = { onSelectTask(taskCard.taskId) },
                modifier = Modifier.padding(bottom = 6.dp)
            )
        }
    }
}
