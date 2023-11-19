package com.integrame.app.dashboard.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.integrame.app.ui.navigation.IntegraMeNavGraph
import com.integrame.app.ui.navigation.IntegraMeScreen

object StudentDashboardNavGraph : IntegraMeNavGraph {
    // NOTE: Es el menú principal del estudiante (no en DashboardScreen)
    override val route: String = "dashboard"

    object StudentTask: IntegraMeScreen {
        override val route: String = StudentDashboardNavGraph.getSubRoute("task")
        const val taskIdArg = "taskId"
        val routeWithArgs = "$route/{${taskIdArg}}"
        val arguments = listOf(
            navArgument(taskIdArg) { type = NavType.IntType }
        )

        fun buildRouteWithArgs(taskId: Int) : String {
            return "${route}/${taskId}"
        }
    }
}