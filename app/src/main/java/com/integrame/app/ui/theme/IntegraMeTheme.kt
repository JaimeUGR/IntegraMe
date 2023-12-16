package com.integrame.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.integrame.app.ui.viewmodel.ThemeViewModel

object AppTheme {
    val palettes = listOf(
        com.integrame.app.ui.theme.palettes.orange.palette,
        com.integrame.app.ui.theme.palettes.candy.palette,
        com.integrame.app.ui.theme.palettes.lollipop.palette,
        com.integrame.app.ui.theme.palettes.diamond.palette,
        com.integrame.app.ui.theme.palettes.emerald.palette
    )
}

@Composable
fun IntegraMeTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    themeViewModel: ThemeViewModel = hiltViewModel(),
    content: @Composable() () -> Unit
) {
    val paletteId by themeViewModel.paletteIdFlow.collectAsStateWithLifecycle()

    // Determinar la paleta seleccionada (en caso de error se pone la por defecto)
    val palette =
        if (paletteId >= AppTheme.palettes.size) {
            com.integrame.app.ui.theme.palettes.orange.palette
        } else {
            AppTheme.palettes[paletteId]
        }

    val colors = if (!useDarkTheme) {
        palette.lightColors
    } else {
        palette.darkColors
    }

    // Importar la tipografía desde R

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}