package com.integrame.app.login.ui.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.integrame.app.login.ui.screens.StudentAuthScreen
import com.integrame.app.login.ui.screens.StudentLoginScreen
import com.integrame.app.login.ui.screens.TeacherLoginScreen
import kotlinx.coroutines.delay

fun NavGraphBuilder.loginGraph(navController: NavController) {
    navigation(
        startDestination = LoginNavGraph.StudentLogin.route,
        route = LoginNavGraph.route
    ) {
        composable(route = LoginNavGraph.StudentLogin.route) {
            StudentLoginScreen(
                onIdentitySelected = { userId ->
                    navController.navigate(
                        route = LoginNavGraph.StudentAuth.buildRouteWithArgs(userId = userId)
                    )
                }
            )
        }
        composable(
            route = LoginNavGraph.StudentAuth.routeWithArgs,
            arguments = LoginNavGraph.StudentAuth.arguments
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt(LoginNavGraph.StudentAuth.userIdArg)

            if (userId == null)
                LaunchedEffect(key1 = Unit) {
                    delay(2000)
                    // Mostrar un error y volver a la pantalla de login
                    navController.navigate(route = LoginNavGraph.StudentLogin.route)
                }
            else
                StudentAuthScreen(
                    userId = userId,
                    onErrorScreenButtonClick = {
                        navController.navigate(route = LoginNavGraph.StudentLogin.route)
                    }
                )
        }
        composable(route = LoginNavGraph.TeacherLogin.route) {
            TeacherLoginScreen()
        }
    }
}
