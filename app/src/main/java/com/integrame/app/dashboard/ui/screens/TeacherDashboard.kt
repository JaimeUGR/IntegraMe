package com.integrame.app.dashboard.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedButton
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
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.integrame.app.core.data.model.content.ImageContent
import com.integrame.app.core.data.model.content.RemoteImage
import com.integrame.app.core.data.model.user.TeacherProfile
import com.integrame.app.core.ui.components.DynamicImage
import com.integrame.app.dashboard.ui.navigation.TeacherDashboardNavGraph
import com.integrame.app.teacher.data.model.task.TaskInfo
import com.integrame.app.teacher.ui.screens.MakeTaskScreen
import com.integrame.app.teacher.ui.screens.StudentsScreen

@Composable
fun TeacherDashboard(
    teacherProfile: TeacherProfile,
    onSignOut: () -> Unit,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    // Crear aquí Scaffold con el grafo de navegación que corresponda
    NavHost(
        navController = navController,
        startDestination = TeacherDashboardNavGraph.route,
        modifier = modifier
    ) {
        composable(route = TeacherDashboardNavGraph.route) {
            val menuActions = listOf(
                MenuAction(
                    displayName = "Students",
                    displayImage = null,
                    onClick = {
                        navController.navigate(TeacherDashboardNavGraph.Students.route)
                    }
                ),
                MenuAction(
                    displayName = "Tareas",
                    displayImage = null,
                    onClick = {
                        navController.navigate("")
                    }
                ),
                MenuAction(
                    displayName = "Plantillas",
                    displayImage = RemoteImage("https://imgs.search.brave.com/FacTMTyNHPKLp02C6oT8c-JmM70hAYetdoWY0b4eAgI/rs:fit:860:0:0/g:ce/aHR0cHM6Ly9ncmF0/aXNvZ3JhcGh5LmNv/bS93cC1jb250ZW50/L3VwbG9hZHMvMjAy/My8wNi9ncmF0aXNv/Z3JhcGh5LXdhdGVy/bWVsb24tcG9wc2lj/bGUtZnJlZS1zdG9j/ay1waG90by04MDB4/NTI1LmpwZw", 0, ""),
                    onClick = {}
                )
            )

            TeacherDashboardScreen(
                teacherProfile = teacherProfile,
                menuActions = menuActions,
                onSignOut = onSignOut,
                onPressNotifications = {},
                onPressProfile = {},
                modifier = Modifier.fillMaxSize()
            )
        }

        composable(route = TeacherDashboardNavGraph.Students.route) {
            StudentsScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onPressHome = { },
                modifier = Modifier.fillMaxSize(),

            )

        }

        /*
        composable(route = TeacherDashboardNavGraph.Task.route){
            MakeTaskScreen(
                onNavigateBack = {
                    navController.popBackStack()
                                 },
                modifier = Modifier.fillMaxSize()
            )
        }

         */

        composable(route = TeacherDashboardNavGraph.Profile.route) {

        }

        composable(route = TeacherDashboardNavGraph.Notifications.route) {

        }
    }
}

private data class MenuAction(
    val displayName: String,
    val displayImage: ImageContent?,
    val onClick: () -> Unit
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TeacherDashboardScreen(
    teacherProfile: TeacherProfile,
    menuActions: List<MenuAction>,
    onSignOut: () -> Unit,
    onPressNotifications: () -> Unit,
    onPressProfile: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Menú Docente",
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
                            image = teacherProfile.avatar,
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
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            // TODO: Si únicamente estarán las acciones seleccionables, quitar la column
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(menuActions) { menuAction ->
                    Button(
                        onClick = menuAction.onClick,
                        modifier = Modifier.height(176.dp),
                        shape = RoundedCornerShape(16.dp),
                        contentPadding = PaddingValues(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer,
                            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    ) {
                        val hasDisplayImage = menuAction.displayImage != null

                        Column(
                            verticalArrangement = Arrangement.SpaceBetween,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            if (hasDisplayImage) {
                                DynamicImage(
                                    image = menuAction.displayImage!!,
                                    modifier = Modifier
                                        .padding(bottom = 12.dp)
                                        .weight(1f)
                                )
                            }

                            Text(
                                text = menuAction.displayName,
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.titleMedium
                                    .copy(
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 20.sp
                                    )
                            )
                        }
                    }
                }
            }
        }
    }
}
