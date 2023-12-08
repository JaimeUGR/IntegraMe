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
import androidx.compose.foundation.layout.onConsumedWindowInsetsChanged
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.integrame.app.core.ui.components.ErrorCard
import com.integrame.app.core.ui.components.appbar.PaginatedBottomAppBar
import com.integrame.app.core.ui.components.appbar.StudentTaskTopAppBar
import com.integrame.app.login.ui.navigation.LoginNavGraph
import com.integrame.app.login.ui.screens.IdentityCardGrid
import com.integrame.app.login.ui.viewmodel.StudentLoginUIState
import com.integrame.app.login.ui.viewmodel.StudentLoginViewModel
import com.integrame.app.teacher.data.model.task.TaskInfo
import com.integrame.app.teacher.ui.viewmodel.AsignTaskScreenViewModel
import com.integrame.app.teacher.ui.viewmodel.SelectStudentUIState
import com.integrame.app.ui.theme.IntegraMeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AsignTaskScreen(
    onNavigateBack: () -> Unit,
    onPressHome: () -> Unit,
    modifier: Modifier = Modifier,

) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "asignTask",
        modifier = modifier
        ) {

        // Usar la pantalla ya hecha?
        // Crear FakeResources de plantillas ?
        composable(route = "asignTask") {
            SelectStudentScreen(
                onIdentitySelected = {
                    navController.navigate(
                        route = "selectTaskModel"
                    )
                },
                onPressHome = { },
                onNavigateBack = {
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxSize()
            )
        }
        composable(route = "selectTaskModel") {
            SelectTaskModelScreen(
                onPressHome = { /*TODO*/ },
                onNavigateBack = {
                    navController.popBackStack()
                },
                onCustomModelSelect = {
                    navController.navigate("setTaskInfo")
                },
                onTaskModelSelect = {
                    navController.navigate("setTaskInfo")
                },
                asignTaskScreenViewModel = asignTaskScreenViewModel
            )

        }

        composable(route = "setTaskInfo") {
            SetTaskInfoScreen(
                onPressHome = { /*TODO*/ },
                onNavigateBack = {
                    navController.popBackStack()
                },
                onContinue = {
                     navController.navigate("setDateAndRewardInfo")
                },
                asignTaskScreenViewModel = asignTaskScreenViewModel
            )

        }

        composable(route = "setDateAndRewardInfo"){
            SetDateAndRewardTaskInfoScreen(
                onPressHome = { /*TODO*/ },
                onNavigateBack = {
                    navController.popBackStack()
                },
                onStudentListReturn = {
                     navController.navigate("asignTask")
                },
                asignTaskScreenViewModel = asignTaskScreenViewModel
            )
        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SelectStudentScreen(
    onIdentitySelected: (Int) -> Unit,
    onPressHome: () -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    asignTaskScreenViewModel: AsignTaskScreenViewModel = hiltViewModel()

) {

    val selectUIState = asignTaskScreenViewModel.selectStudentUIState

    Text(text = "Estas en la primera pantalla de asignar tarea")
    Scaffold(
            topBar = {
                StudentTaskTopAppBar(
                    title = "Selección de Alumnos",
                    onNavigateBack = onNavigateBack,
                    onPressHome = { /*TODO*/ },
                    onPressChat = { /*TODO*/ }
                )
            },
        modifier = modifier
    ) { innerPadding ->
        when (selectUIState) {
            is SelectStudentUIState.IdentitiesReady -> {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)) {
                    IdentityCardGrid(
                        asignTaskScreenViewModel.getIdentityCardsPage(),
                        onIdentityCardClick = onIdentitySelected,
                        modifier = Modifier
                            .align(Alignment.Center)
                    )
                }

            }
            is SelectStudentUIState.Loading -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier
                        .align(Alignment.Center)
                        .size(80.dp))
                }
            }
            is SelectStudentUIState.Error -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    ErrorCard(
                        errorDescription = selectUIState.error,
                        errorButtonText = "Cerrar",
                        errorButtonImage = {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = "Cerrar",
                                tint = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        },
                        onPressContinue = { asignTaskScreenViewModel.reloadIdentityCards() },
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SelectTaskModelScreen(
    onPressHome: () -> Unit,
    onNavigateBack: () -> Unit,
    onTaskModelSelect: () -> Unit,
    onCustomModelSelect: () -> Unit,
    modifier: Modifier = Modifier,
    asignTaskScreenViewModel: AsignTaskScreenViewModel

) {
    LaunchedEffect(Unit){
        asignTaskScreenViewModel.loadTaskModels()
        
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
                onClick = { onCustomModelSelect },
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
                            onTaskModelSelect
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
    onContinue: () -> Unit,
    asignTaskScreenViewModel: AsignTaskScreenViewModel,
    modifier: Modifier = Modifier,

) {
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
                onClick = { onContinue },
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
    onStudentListReturn: () -> Unit,
    asignTaskScreenViewModel: AsignTaskScreenViewModel,
    modifier: Modifier = Modifier

) {
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
                onClick = { onStudentListReturn },
            ) {
                Text(text = "Guardar",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium))
            }

        }

    }

}

