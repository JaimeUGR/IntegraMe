package com.integrame.app.tasks.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.integrame.app.core.data.fake.FakeResources
import com.integrame.app.core.ui.components.DynamicImage
import com.integrame.app.ui.theme.IntegraMeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstAsignTaskScreen(
    modifier: Modifier = Modifier
) {
    LaunchedEffect(Unit) {

    }

    val listaNumeros = List(4) {
        0
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            Surface(
                modifier = Modifier.height(80.dp),
                color = MaterialTheme.colorScheme.primaryContainer
            ) {}
        },

    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 30.dp, vertical = 30.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                modifier = Modifier.align(Alignment.End),
                onClick = { /*TODO*/ },
            ) {
                Text(text = "Personalizar",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium))
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text("Elige plantilla ",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium))

            Spacer(modifier = Modifier.height(24.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                userScrollEnabled = false,
            ) {

                items(listaNumeros) {
                    Box(modifier = Modifier
                        .requiredSize(150.dp)
                        .background(Color.Gray)
                        .clickable {
                        }
                    ) {

                    }
                }

            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = { /*TODO*/ },
            ) {
                Text(text = "Crear nueva tarea",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium))
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun FirstAsignTaskScreenPreview() {
    IntegraMeTheme {
        FirstAsignTaskScreen(
            modifier = Modifier.fillMaxSize()
        )
    }
}