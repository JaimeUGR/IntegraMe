package com.integrame.app.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.integrame.app.core.data.model.content.ImageContent
import com.integrame.app.core.data.model.content.VectorImage
import com.integrame.app.ui.theme.IntegraMeTheme

@Composable
fun ErrorCard(
    errorDescription: String,
    errorButtonText: String,
    errorButtonImage: @Composable () -> Unit,
    onPressContinue: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Filled.ErrorOutline,
                contentDescription = "Imagen roja de error ocurrido",
                modifier = Modifier.size(80.dp),
                tint = Color.Red
            )

            Text(
                text = "Error",
                modifier = Modifier.padding(top = 8.dp, bottom = 4.dp),
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
            )
            Text(text = errorDescription)

            Spacer(modifier = Modifier.height(12.dp))

            Button(onClick = onPressContinue) {
                Text(text = errorButtonText)
                Spacer(modifier = Modifier.width(16.dp))
                errorButtonImage()
            }
        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun PreviewErrorCard() {
    IntegraMeTheme {
        ErrorCard(
            errorDescription = "Error 404: Not Found",
            errorButtonText = "Reintentar",
            errorButtonImage = {
                DynamicImage(
                    VectorImage(Icons.Filled.Replay, 0, "Retry")
                )
            },
            onPressContinue = {}
        )
    }
}
