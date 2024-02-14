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

package com.integrame.app.dashboard.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.integrame.app.ui.navigation.IntegraMeNavGraph
import com.integrame.app.ui.navigation.IntegraMeScreen

object StudentDashboardNavGraph: IntegraMeNavGraph {
    // NOTE: Es el menú principal del estudiante (no en DashboardScreen)
    override val route: String = "dashboard"

    object StudentProfile: IntegraMeScreen {
        override val route: String = StudentDashboardNavGraph.getSubRoute("profile")
    }

    object StudentTask: IntegraMeScreen {
        override val route: String = StudentDashboardNavGraph.getSubRoute("task")
        const val taskIdArg = "taskId"
        val routeWithArgs = "$route/{${taskIdArg}}"
        val arguments = listOf(
            navArgument(taskIdArg) { type = NavType.IntType }
        )

        fun buildRouteWithArgs(taskId: Int): String {
            return "${route}/${taskId}"
        }
    }
}