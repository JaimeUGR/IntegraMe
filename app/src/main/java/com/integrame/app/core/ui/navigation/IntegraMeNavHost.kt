package com.integrame.app.core.ui.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.integrame.app.login.ui.navigation.LoginNavGraph
import com.integrame.app.login.ui.navigation.loginGraph

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
                    Text(text = "Navegar siguiente")
                }
            }
        }
        composable("test_screen") {
            Text(text = "Soy la test_screen")
        }

        loginGraph(navController)
    }
}

