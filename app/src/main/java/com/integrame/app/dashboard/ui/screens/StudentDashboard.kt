package com.integrame.app.dashboard.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.integrame.app.dashboard.ui.navigation.StudentDashboardNavGraph

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentDashboard(
    onSignOut: () -> Unit,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = StudentDashboardNavGraph.route,
        modifier = modifier.fillMaxSize()
    ) {
        composable(route = StudentDashboardNavGraph.route) {
            // Pantalla principal (la de las actividades)

            Column {
                Text(text = "Eres un alumno")
                Button(onClick = onSignOut) {
                    Text(text = "Cerrar sesión")
                }
            }
        }
    }
}

@Composable
fun StudentDashboardTopAppBar(
    navController: NavController,
    modifier: Modifier = Modifier
) {

}
