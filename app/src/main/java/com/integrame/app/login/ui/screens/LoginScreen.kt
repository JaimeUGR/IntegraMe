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

package com.integrame.app.login.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.integrame.app.login.ui.navigation.LoginNavGraph
import kotlinx.coroutines.delay

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onAuthorized: () -> Unit
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = LoginNavGraph.StudentLogin.route,
        modifier = modifier
    ) {
        composable(route = LoginNavGraph.StudentLogin.route) {
            StudentLoginScreen(
                onIdentitySelected = { userId ->
                    navController.navigate(
                        route = LoginNavGraph.StudentAuth.buildRouteWithArgs(userId = userId)
                    )
                },
                onTeacherLoginSelected = {
                    navController.navigate(route = LoginNavGraph.TeacherLogin.route)
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
                    // TODO: Mostrar un error y volver a la pantalla de login

                    // TODO: Revisar si esto es correcto
                    navController.navigate(route = LoginNavGraph.StudentLogin.route) {
                        popUpTo(route = LoginNavGraph.StudentLogin.route)
                    }
                }
            else
                StudentAuthScreen(
                    userId = userId,
                    onNavigateBack = {
                        navController.popBackStack()
                    },
                    onAuthorized = onAuthorized
                )
        }
        composable(route = LoginNavGraph.TeacherLogin.route) {
            TeacherLoginScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onAuthorized = onAuthorized
            )
        }
    }
}
