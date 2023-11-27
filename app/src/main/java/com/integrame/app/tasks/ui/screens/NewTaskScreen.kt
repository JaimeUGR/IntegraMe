package com.integrame.app.tasks.ui.screens

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.integrame.app.core.ui.components.PaginatedBottomAppBar
import com.integrame.app.ui.theme.IntegraMeTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTaskScreen(
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
            Text("Nombre tarea: ",
                       style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = "",
                onValueChange = {  },
                singleLine = true
            )

            Text("Fecha inicio: ",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = "",
                onValueChange = {  },
                singleLine = true
            )

            Text("Fecha finalización",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = "",
                onValueChange = {  },
                singleLine = true
            )
            Text("Pasos",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium))
            Button(
                modifier = Modifier.align(Alignment.End),
                onClick = { /*TODO*/ },
            ) {
                Text(text = "+",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium))
            }
            LazyColumn(
                modifier = Modifier.size(190.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(listaNumeros) {
                    Box(modifier = Modifier
                        .requiredSize(92.dp)
                        .background(Color.Gray)
                        .clickable {

                        }
                    ) {}
                }
            }

            Button(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = { /*TODO*/ },
            ) {
                Text(text = "Terminar tarea",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium))
            }


        }


    }

}

@Preview(showSystemUi = true)
@Composable
fun NewTaskScreenPreview() {
    IntegraMeTheme {
        NewTaskScreen(
            modifier = Modifier.fillMaxSize()
        )
    }
}

