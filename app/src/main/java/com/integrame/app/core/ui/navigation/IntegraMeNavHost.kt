package com.integrame.app.core.ui.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
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
import dagger.hilt.android.lifecycle.HiltViewModel

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
            Column(modifier = Modifier.fillMaxSize()) {
                Text(text = "Soy la splash screen")
                Button(onClick = { navController.navigate(route = LoginNavGraph.TeacherLogin.route) }) {
                    Text(text = "Pantalla X")
                }
                Button(onClick = { navController.navigate(route = LoginNavGraph.StudentLogin.route) }) {
                    Text(text = "Pantalla Y")
                }
            }
        }
        composable("test_screen") {
            Text(text = "Soy la test_screen")
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
