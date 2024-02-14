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

package com.integrame.app.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.integrame.app.dashboard.ui.navigation.DashboardNavNavGraph
import com.integrame.app.dashboard.ui.screens.DashboardScreen
import com.integrame.app.login.ui.navigation.LoginNavGraph
import com.integrame.app.login.ui.screens.LoginScreen
import com.integrame.app.ui.screens.SplashScreen

@Composable
fun IntegraMeNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = SplashScreenNav.route
    ) {
        composable(SplashScreenNav.route) {
            SplashScreen(
                onLoadReady = { hasAuthorizedSession ->
                    navController.navigate(
                        if (hasAuthorizedSession) DashboardNavNavGraph.route
                        else LoginNavGraph.route
                    ) {
                        popUpTo(id = navController.graph.id) {
                            inclusive = true
                        }
                    }
                },
                modifier = Modifier.fillMaxSize(),
            )
        }

        composable(LoginNavGraph.route) {
            LoginScreen(
                onAuthorized = {
                    navController.navigate(DashboardNavNavGraph.route) {
                        popUpTo(id = navController.graph.id) {
                            inclusive = true
                        }
                    }
                },
                modifier = Modifier.fillMaxSize()
            )
        }

        composable(DashboardNavNavGraph.route) {
            DashboardScreen(
                onSignOut = {
                    navController.navigate(LoginNavGraph.route) {
                        popUpTo(id = navController.graph.id) {
                            inclusive = true
                        }
                    }
                },
                onAuthorizationError = {
                    navController.navigate(LoginNavGraph.route) {
                        popUpTo(id = navController.graph.id) {
                            inclusive = true
                        }
                    }
                },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
inline fun <reified T: ViewModel> NavBackStackEntry.sharedViewModel(
    navController: NavHostController,
) : T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }

    return viewModel(parentEntry)
}

@Composable
inline fun <reified T: ViewModel> NavBackStackEntry.sharedHiltViewModel(
    navController: NavHostController,
) : T {
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }

    return hiltViewModel(parentEntry)
}
