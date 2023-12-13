package com.integrame.app.dashboard.ui.navigation

import com.integrame.app.ui.navigation.IntegraMeNavGraph
import com.integrame.app.ui.navigation.IntegraMeScreen

object TeacherDashboardNavGraph: IntegraMeNavGraph {
    // NOTE: Es el menú principal del profesor (no en DashboardScreen)
    override val route: String = "dashboard"


    object Profile: IntegraMeScreen {
        override val route = TeacherDashboardNavGraph.getSubRoute("profile")
    }

    object Notifications: IntegraMeScreen {
        override val route = TeacherDashboardNavGraph.getSubRoute("notifications")
    }

    object Students: IntegraMeScreen{
        override val route = TeacherDashboardNavGraph.getSubRoute("selectStudent")
    }

    object Task: IntegraMeScreen{
        override val route = TeacherDashboardNavGraph.getSubRoute("makeTask")
    }

}