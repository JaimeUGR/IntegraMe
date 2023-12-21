package com.integrame.app.dashboard.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.integrame.app.tasks.ui.screens.TaskScheduleScreen
import com.integrame.app.core.data.model.user.StudentProfile
import com.integrame.app.dashboard.ui.navigation.StudentDashboardNavGraph
import com.integrame.app.tasks.ui.screens.TaskScreen

@Composable
fun StudentDashboard(
    studentProfile: StudentProfile,
    onSignOut: () -> Unit,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = StudentDashboardNavGraph.route,
        modifier = modifier.fillMaxSize()
    ) {
        /**
         * Agenda
         */
        composable(route = StudentDashboardNavGraph.route) {
            TaskScheduleScreen(
                studentProfile = studentProfile,
                onSignOut = onSignOut,
                onPressNotifications = {
                    // TODO: Navegar a pantalla de notificaciones
                },
                onPressProfile = {
                     navController.navigate(StudentDashboardNavGraph.StudentProfile.route)
                },
                onSelectTask = { taskId ->
                    navController.navigate(StudentDashboardNavGraph.StudentTask.buildRouteWithArgs(taskId))
                },
                modifier = Modifier.fillMaxSize()
            )
        }

        /**
         * Perfil
         */
        composable(StudentDashboardNavGraph.StudentProfile.route) {
            StudentProfileScreen(
                studentProfile = studentProfile,
                onNavigateBack = { navController.popBackStack() },
                modifier = Modifier.fillMaxSize()
            )
        }

        /**
         * Tarea
         */
        composable(
            route = StudentDashboardNavGraph.StudentTask.routeWithArgs,
            arguments = StudentDashboardNavGraph.StudentTask.arguments
        ) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getInt(StudentDashboardNavGraph.StudentTask.taskIdArg)

            if (taskId == null)
                navController.popBackStack()
            else
                TaskScreen(
                    taskId = taskId,
                    onNavigateBack = {
                        navController.popBackStack()
                    },
                    onPressHome = {
                        navController.popBackStack(
                            route = StudentDashboardNavGraph.route,
                            inclusive = false
                        )
                    },
                    modifier = Modifier.fillMaxSize()
                )
        }
    }
}
