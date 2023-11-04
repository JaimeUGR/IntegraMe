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
import com.integrame.app.login.ui.navigation.LoginNavGraph
import com.integrame.app.login.ui.navigation.loginGraph
import com.integrame.app.login.ui.screens.TestLogin

@Composable
fun IntegraMeNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = SplashScreen.route
    ) {
        composable(SplashScreen.route) {
            com.integrame.app.ui.screens.SplashScreen(
                modifier = Modifier.fillMaxSize(),
                onLoadReady = {
                    navController.navigate(LoginNavGraph.StudentLogin.route) {
                        popUpTo(id = navController.graph.id) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable("test_screen") {
            TestLogin()
        }

        loginGraph(navController)
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
