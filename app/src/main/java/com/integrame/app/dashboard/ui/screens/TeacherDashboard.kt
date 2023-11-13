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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.integrame.app.dashboard.ui.navigation.TeacherDashboardNavGraph

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeacherDashboard(
    onSignOut: () -> Unit,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    Scaffold(
        modifier = modifier
    ) { innerPadding ->
        // Crear aquí Scaffold con el grafo de navegación que corresponda
        NavHost(
            navController = navController,
            startDestination = TeacherDashboardNavGraph.route,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            composable(route = TeacherDashboardNavGraph.route) {
                // Pantalla principal (la de gestión)
                Column {
                    Text(text = "Eres un profesor")
                    Button(onClick = onSignOut) {
                        Text(text = "Cerrar sesión")
                    }
                }
            }
        }
    }
}
