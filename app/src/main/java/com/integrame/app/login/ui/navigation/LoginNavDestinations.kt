package com.integrame.app.login.ui.navigation

import com.integrame.app.ui.navigation.IntegraMeGraph
import com.integrame.app.ui.navigation.IntegraMeScreen

object LoginNavGraph : IntegraMeGraph {
    override val route: String = "auth"

    object StudentLogin : IntegraMeScreen {
        override val route: String = LoginNavGraph.getSubRoute("student")
    }

    object TeacherLogin : IntegraMeScreen {
        override val route: String = LoginNavGraph.getSubRoute("teacher")
    }
}
