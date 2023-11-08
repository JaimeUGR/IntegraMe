package com.integrame.app.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.integrame.app.ui.theme.IntegraMeTheme

@Composable
fun ErrorCard(
    errorDescription: String,
    onPressContinue: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Oh no!")
        Text(text = errorDescription)

        Button(onClick = onPressContinue) {
            Text(text = "Aceptar")
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewErrorCard() {
    IntegraMeTheme {
        ErrorCard(
            errorDescription = "Error 404: Not Found",
            onPressContinue = {}
        )
    }
}
