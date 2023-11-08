package com.integrame.app.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    LaunchedEffect(Unit) {
        // TODO: Cargar la sesión del usuario
    }

    // TODO: Añadir Scaffold y cargar de forma condicional la pantalla del profesor o del alumno
    // Además, añadir la navegación para soportar este subgrafo
}
