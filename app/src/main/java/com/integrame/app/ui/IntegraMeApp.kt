package com.integrame.app.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.integrame.app.ui.navigation.IntegraMeNavHost
import com.integrame.app.ui.theme.IntegraMeTheme

@Composable
fun IntegraMeApp() {
    IntegraMeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            IntegraMeNavHost(
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
