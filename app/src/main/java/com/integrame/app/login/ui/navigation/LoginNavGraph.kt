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

package com.integrame.app.login.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.integrame.app.ui.navigation.IntegraMeNavGraph
import com.integrame.app.ui.navigation.IntegraMeScreen

object LoginNavGraph : IntegraMeNavGraph {
    override val route: String = "login"

    object StudentLogin : IntegraMeScreen {
        override val route: String = LoginNavGraph.getSubRoute("student")
    }

    object StudentAuth : IntegraMeScreen {
        override val route: String = LoginNavGraph.getSubRoute("studentAuth")
        const val userIdArg = "user_id"
        val routeWithArgs = "$route/{${userIdArg}}"
        val arguments = listOf(
            navArgument(userIdArg) { type = NavType.IntType }
        )

        fun buildRouteWithArgs(userId: Int) : String {
            return "${route}/${userId}"
        }
    }

    object TeacherLogin : IntegraMeScreen {
        override val route: String = LoginNavGraph.getSubRoute("teacher")
    }
}
