package com.integrame.app.tasks.ui.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.EventNote
import androidx.compose.material3.CenterAlignedTopAppBar
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.integrame.app.core.data.fake.FakeResources
import com.integrame.app.core.data.model.user.StudentProfile
import com.integrame.app.core.ui.components.DynamicImage
import com.integrame.app.core.ui.components.appbar.PaginatedBottomAppBar
import com.integrame.app.tasks.ui.components.TaskCard
import com.integrame.app.tasks.ui.viewmodel.TaskScheduleScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScheduleScreen(
    studentProfile: StudentProfile,
    onSignOut: () -> Unit,
    onPressNotifications: () -> Unit,
    onPressProfile: () -> Unit,
    onSelectTask: (Int) -> Unit,
    modifier: Modifier = Modifier,
    taskScheduleScreenViewModel: TaskScheduleScreenViewModel = hiltViewModel()
) {
    // TODO: Hay que cargar las actividades aquí

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
            PaginatedBottomAppBar(
                currentPage = 0,
                isFirstPage = true,
                isLastPage = true,
                onPressNext = { /*TODO*/ },
                onPressPrevious = { /*TODO*/ },
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        }
    ) { innerPadding ->
        // TODO: La función taskSchedule recibe las tareas ya paginadas
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Divider(
                        modifier = Modifier.weight(1f),
                        thickness = 3.dp,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Icon(
                        imageVector = Icons.Outlined.EventNote,
                        contentDescription = "Icono tareas",
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .size(40.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Divider(
                        modifier = Modifier.weight(1f),
                        thickness = 3.dp,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            items(
                items = FakeResources.taskCards,
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
}
