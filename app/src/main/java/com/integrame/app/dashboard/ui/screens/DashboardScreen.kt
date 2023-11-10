package com.integrame.app.dashboard.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.integrame.app.core.data.model.user.StudentProfile
import com.integrame.app.core.data.model.user.TeacherProfile
import com.integrame.app.dashboard.ui.viewmodel.DashboardUIState
import com.integrame.app.dashboard.ui.viewmodel.DashboardViewModel

@Composable
fun DashboardScreen(
    onSignOut: () -> Unit,
    modifier: Modifier = Modifier,
    dashboardViewModel: DashboardViewModel = hiltViewModel()
) {
    val dashboardUIState = dashboardViewModel.dashboardUIState

    if (dashboardUIState is DashboardUIState.Loading) {
        Box(modifier = modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier
                .align(Alignment.Center)
                .size(80.dp))
        }
        return
    }
    else if (dashboardUIState is DashboardUIState.SignedOut) {
        LaunchedEffect(Unit) {
            onSignOut()
        }
        return
    }

    // Coger el perfil del usuario y determinar el tipo de usuario. Si es niño, pasar
    // las adaptaciones de contenido correspondientes a la pantalla

    if (dashboardUIState is DashboardUIState.ProfileReady)
    {
        when (val profile = dashboardUIState.profile) {
            is StudentProfile -> {
                StudentDashboard(
                    onSignOut = { dashboardViewModel.signOut() }
                )
            }
            is TeacherProfile -> {
                TeacherDashboard(
                    onSignOut = { dashboardViewModel.signOut() }
                )
            }
        }
    }
}
