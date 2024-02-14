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


}