package com.integrame.app.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// NOTE: La primera página internamente es la 0 pero de forma textual se le suma 1
@Composable
fun PaginatedBottomAppBar(
    currentPage: Int,
    isFirstPage: Boolean,
    isLastPage: Boolean,
    onPressNext: () -> Unit,
    onPressPrevious: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.surface
) {
    BottomAppBar(
        modifier = modifier,
        containerColor = containerColor
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = onPressPrevious,
                enabled = !isFirstPage
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Retroceder a la página $currentPage", // currentPage - 1 + 1
                    modifier = Modifier.size(30.dp)
                )
            }
            Button(
                onClick = onPressNext,
                enabled = !isLastPage
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = "Avanzar a la página ${currentPage + 2}", // currentPage + 1 + 1
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }
}