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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.integrame.app.core.ui.components.ErrorCard
import com.integrame.app.core.ui.components.appbar.StudentTaskTopAppBar
import com.integrame.app.core.ui.components.appbar.TeacherCenterAlignedTopAppBar
import com.integrame.app.login.ui.screens.IdentityCardGrid
import com.integrame.app.teacher.ui.viewmodel.SelectStudentScreenViewModel
import com.integrame.app.teacher.ui.viewmodel.SelectStudentUIState
import com.integrame.app.teacher.ui.viewmodel.SelectTaskModelScreenViewModel
import com.integrame.app.ui.navigation.sharedHiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentsScreen(
    onNavigateBack: () -> Unit,
    onPressHome: () -> Unit,
    modifier: Modifier = Modifier,

) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "selectStudent",
        modifier = modifier

        ) {

        composable(route = "selectStudent") {
            SelectStudentScreen(
                onIdentitySelected = {
                    navController.navigate(
                        route = "selectTaskModel"
                    )
                },
                onPressHome = { },
                onNavigateBack = onNavigateBack,
                modifier = Modifier.fillMaxSize()
            )
        }
        composable(route = "selectTaskModel") { navBackStackEntry ->
            val viewModel: SelectTaskModelScreenViewModel = navBackStackEntry.sharedHiltViewModel(
                navController = navController
            )

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
                onCreateTask = {
                    navController.navigate("makeTask")
                },
                selectTaskModelScreenViewModel = viewModel
            )

        }

        composable(route = "makeTask"){
            MakeTaskScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxSize() )
        }

        composable(route = "setTaskInfo") { navBackStackEntry ->
            val viewModel: SelectTaskModelScreenViewModel = navBackStackEntry.sharedHiltViewModel(
                navController = navController
            )
            SetTaskInfoScreen(
                onPressHome = { /*TODO*/ },
                onNavigateBack = {
                    navController.popBackStack()
                },
                onContinue = {
                     navController.navigate("setDateAndRewardInfo")
                },
                selectTaskModelScreenViewModel = viewModel

            )

        }

        composable(route = "setDateAndRewardInfo"){ navBackStackEntry ->
            val viewModel: SelectTaskModelScreenViewModel = navBackStackEntry.sharedHiltViewModel(
                navController = navController
            )

            SetDateAndRewardTaskInfoScreen(
                onPressHome = { /*TODO*/ },
                onNavigateBack = {
                    navController.popBackStack()
                },
                onStudentListReturn = {
                     navController.navigate("selectStudent")
                },
                selectTaskModelScreenViewModel = viewModel
            )
        }

    }

}

// 1
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SelectStudentScreen(
    onIdentitySelected: (Int) -> Unit,
    onPressHome: () -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    selectStudentScreenViewModel: SelectStudentScreenViewModel = hiltViewModel()

) {

    val selectUIState = selectStudentScreenViewModel.selectStudentUIState

    Text(text = "Estas en la primera pantalla de asignar tarea")
    Scaffold(
            topBar = {
                // CenterAling
                TeacherCenterAlignedTopAppBar(
                    title = "Selección de Alumnos",
                    onNavigateBack = onNavigateBack,
                    onPressHome = { /*TODO*/ },
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
                        selectStudentScreenViewModel.getIdentityCardsPage(),
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
                        onPressContinue = { selectStudentScreenViewModel.reloadIdentityCards() },
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }

}

// 2
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SelectTaskModelScreen(
    onPressHome: () -> Unit,
    onNavigateBack: () -> Unit,
    onTaskModelSelect: () -> Unit,
    onCustomModelSelect: () -> Unit,
    onCreateTask: () -> Unit,
    modifier: Modifier = Modifier,
    selectTaskModelScreenViewModel: SelectTaskModelScreenViewModel

) {
    LaunchedEffect(Unit){
        // asignTaskScreenViewModel.loadTaskModels()
        
    }
    
    val numberList = List(4){
        0
    }
    
    Scaffold (
        modifier  = modifier,
        topBar = {
            TeacherCenterAlignedTopAppBar(
                title = "Selección o personalización de plantilla",
                onNavigateBack = onNavigateBack,
                onPressHome = { /*TODO*/ },
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
                    Column {
                        Box(
                            modifier = Modifier
                                .requiredSize(150.dp)
                                .background(Color.Gray)
                                .clickable {

                                }
                        ) {
                            // Contenido del cuadrado (Plantillas de tareas)
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .height(40.dp),
                            onClick = onCustomModelSelect,
                        ) {
                            Text(
                                text = "Personalizar",
                                style = MaterialTheme.typography.titleLarge.copy(
                                    fontWeight = FontWeight.Medium
                                )
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = onCreateTask,
            ) {
                Text(
                    text = "Crear nueva tarea",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium)
                )
            }
        }
    }

}

// 3
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SetTaskInfoScreen(
    onPressHome: () -> Unit,
    onNavigateBack: () -> Unit,
    onContinue: () -> Unit,
    modifier: Modifier = Modifier,
    selectTaskModelScreenViewModel: SelectTaskModelScreenViewModel

) {
    LaunchedEffect(Unit){

    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TeacherCenterAlignedTopAppBar(
                title = "Personalizar plantilla",
                onNavigateBack = onNavigateBack,
                onPressHome = { /*TODO*/ },
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
                onClick =  onContinue ,
            ) {
                Text(text = "Continuar",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium))
            }
        }
    }
}

// 4
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SetDateAndRewardTaskInfoScreen(
    onPressHome: () -> Unit,
    onNavigateBack: () -> Unit,
    onStudentListReturn: () -> Unit,
    modifier: Modifier = Modifier,
    selectTaskModelScreenViewModel: SelectTaskModelScreenViewModel

) {
    LaunchedEffect(Unit){

    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TeacherCenterAlignedTopAppBar(
                title = "Establecer fecha y recompensa de la tarea",
                onNavigateBack = onNavigateBack,
                onPressHome = { /*TODO*/ },
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

