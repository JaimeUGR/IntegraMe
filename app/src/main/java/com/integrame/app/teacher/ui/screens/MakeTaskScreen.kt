package com.integrame.app.teacher.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.integrame.app.core.ui.components.appbar.StudentTaskTopAppBar
import com.integrame.app.core.ui.components.appbar.TeacherCenterAlignedTopAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MakeTaskScreen(
    onNavigateBack: () -> Unit,
    modifier: Modifier
){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "makeTask",
        modifier = modifier

    ){
        composable(route = "makeTask"){
            NewTaskScreen(modifier =  Modifier.fillMaxSize(),
                onNewStep = {
                    navController.navigate("newStep")
                },
                onNavigateBack = onNavigateBack

            )
        }

        composable(route = "newStep"){
            NewStepTaskScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxSize(),
                onNewTaskBack = {
                    navController.popBackStack()
                }
            )

        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NewTaskScreen(
    modifier: Modifier,
    onNewStep: () -> Unit,
    onNavigateBack: () -> Unit
){
    LaunchedEffect(Unit) {

    }
    val listaNumeros = List(9) {
        0
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TeacherCenterAlignedTopAppBar(
                title = "Creación de tarea" ,
                onNavigateBack = onNavigateBack,
                onPressHome = { /*TODO*/ })

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
                onClick = onNewStep ,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NewStepTaskScreen(
    onNavigateBack: () -> Unit,
    onNewTaskBack: () -> Unit,
    modifier: Modifier = Modifier,
){
    LaunchedEffect(Unit) {

    }

    Scaffold(
        modifier = modifier,
        topBar =  {
            TeacherCenterAlignedTopAppBar(
                title = "Añadir nuevo paso" ,
                onNavigateBack = onNavigateBack,
                onPressHome = { /*TODO*/ })

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
            Text("Titulo: ",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = "",
                onValueChange = {  },
                singleLine = true
            )
            Text("Descripción: ",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(width = 20.dp, height = 200.dp),
                value = "",
                onValueChange = {  },
            )

            Text("Audio: ",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium))
            Button(
                modifier = Modifier.align(Alignment.End),
                onClick = { /*TODO*/ },
            ) {
                Text(text = "+",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium))
            }

            Text("Imagen:",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium))
            Button(
                modifier = Modifier.align(Alignment.End),
                onClick = { /*TODO*/ },
            ) {
                Text(text = "+",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium))
            }
            Text("Video: ",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium))
            Button(
                modifier = Modifier.align(Alignment.End),
                onClick = { /*TODO*/ },
            ) {
                Text(text = "+",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium))
            }

            Button(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = onNewTaskBack,
            ) {
                Text(text = "Añadir Paso",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium))
            }
        }


    }

}


