package com.integrame.app.login.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.integrame.app.login.ui.screens.StudentLogin
import com.integrame.app.login.ui.screens.TeacherLogin

fun NavGraphBuilder.loginGraph(navController: NavController) {
    navigation(
        startDestination = LoginNavGraph.StudentLogin.route,
        route = LoginNavGraph.route
    ) {
        composable(route = LoginNavGraph.StudentLogin.route) {
            StudentLogin()
        }
        composable(route = LoginNavGraph.TeacherLogin.route) {
            TeacherLogin()
        }
    }
}
