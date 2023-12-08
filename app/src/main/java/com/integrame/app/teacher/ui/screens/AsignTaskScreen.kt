package com.integrame.app.teacher.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.integrame.app.core.ui.components.appbar.StudentTaskTopAppBar
import com.integrame.app.login.ui.screens.IdentityCardGrid
import com.integrame.app.login.ui.screens.StudentLoginScreen
import com.integrame.app.teacher.data.model.task.TaskInfo
import com.integrame.app.ui.theme.IntegraMeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AsignTaskScreen(
    taskInfo: TaskInfo,
    onNavigateBack: () -> Unit,
    onPressHome: () -> Unit,
    modifier: Modifier = Modifier

) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "asignTask",
        modifier = modifier
        ){
        composable(route = "asignTask"){
            SelectStudentScreen(
                onPressHome = { /*TODO*/ },
                onNavigateBack = {
                navController.popBackStack()
            },
                modifier = Modifier.fillMaxSize()
            )
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SelectStudentScreen(
    onPressHome: () -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
){
    Text(text = "Estas en la primera pantalla de asignar tarea")
    //IdentityCardGrid(identityCardList = emptyList(), onIdentityCardClick = {}, modifier = Modifier)

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SelectTaskModelScreen(
    onPressHome: () -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier

){
    LaunchedEffect(Unit){
        
    }
    
    val numberList = List(4){
        0
    }
    
    Scaffold (
        modifier  = modifier,
        topBar = {
            StudentTaskTopAppBar(
                title = "Selección o personalización de plantilla",
                onNavigateBack = onNavigateBack,
                onPressHome = { /*TODO*/ },
                onPressChat = { /*TODO*/ }
            )
        },
    ){ innerPadding ->
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

                items(numberList) {
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



@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SetTaskInfoScreen(
    onPressHome: () -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier

){
    LaunchedEffect(Unit){

    }

    Scaffold(
        modifier = modifier,
        topBar = {
            StudentTaskTopAppBar(
                title = "Personalizar plantilla",
                onNavigateBack = onNavigateBack,
                onPressHome = { /*TODO*/ },
                onPressChat = { /*TODO*/ }
            )
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
            Text("Título: ",
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
                    .size(width = 20.dp, height = 100.dp),
                value = "",
                onValueChange = {  },
            )
            Text("Pasos: ",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium))
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
                Text(
                    text = "+",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium)
                )
            }
            Button(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = { /*TODO*/ },
            ) {
                Text(text = "Continuar",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SetDateAndRewardTaskInfoScreen(
    onPressHome: () -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier

){
    LaunchedEffect(Unit){

    }

    Scaffold(
        modifier = modifier,
        topBar = {
            StudentTaskTopAppBar(
                title = "Establecer fecha y recompensa de la tarea",
                onNavigateBack = onNavigateBack,
                onPressHome = { /*TODO*/ },
                onPressChat = { /*TODO*/ })
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

