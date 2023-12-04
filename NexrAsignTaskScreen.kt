package com.integrame.app.tasks.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.integrame.app.ui.theme.IntegraMeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NextTaskScreen(
    modifier: Modifier = Modifier
) {
    LaunchedEffect(Unit) {

    }
    val listaNumeros = List(9) {
        0
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            Surface(
                modifier = Modifier.height(80.dp),
                color = MaterialTheme.colorScheme.primaryContainer
            ) {}
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text("Fecha inicio: ",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = "",
                onValueChange = {  },
                singleLine = true
            )

            Text("Fecha finalización: ",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = "",
                onValueChange = {  },
                singleLine = true
            )

            Text("Premio:",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = "",
                onValueChange = {  },
                singleLine = true
            )
            Button(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = { /*TODO*/ },
            ) {
                Text(text = "Guardar",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium))
            }


        }


    }

}

@Preview(showSystemUi = true)
@Composable
fun NextTaskScreenPreview() {
    IntegraMeTheme {
        NextTaskScreen(
            modifier = Modifier.fillMaxSize()
        )
    }
}