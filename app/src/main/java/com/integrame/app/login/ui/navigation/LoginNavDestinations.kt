package com.integrame.app.login.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.integrame.app.ui.navigation.IntegraMeGraph
import com.integrame.app.ui.navigation.IntegraMeScreen

object LoginNavGraph : IntegraMeGraph {
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
