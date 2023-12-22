package com.integrame.app.teacher.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.integrame.app.core.data.model.content.ImageContent
import com.integrame.app.core.ui.components.DynamicImage
import com.integrame.app.core.ui.components.ErrorCard
import com.integrame.app.core.ui.components.appbar.TeacherCenterAlignedTopAppBar
import com.integrame.app.login.ui.screens.IdentityCardGrid
import com.integrame.app.teacher.ui.viewmodel.SelectStudentScreenViewModel
import com.integrame.app.teacher.ui.viewmodel.SelectStudentUIState
import com.integrame.app.teacher.ui.viewmodel.SelectTaskModelScreenViewModel
import com.integrame.app.ui.navigation.sharedHiltViewModel

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
                onIdentitySelected = { userId ->
                    navController.navigate(
                        route = "selectActionStudent/${userId}",

                    )
                },
                onPressHome = { },
                onNavigateBack = onNavigateBack,
                modifier = Modifier.fillMaxSize()
            )
        }

        composable(
            route = "selectActionStudent/{userId}",
            arguments = listOf(navArgument("userId") {type = NavType.IntType})
        ) { navBackStackEntry ->
            val userId = navBackStackEntry.arguments?.getInt("userId")!!

            val menuActions = listOf(
                MenuActionStudent(
                    displayName = "Asignar tarea a alumno",
                    displayImage = null,
                    onClick = {
                        navController.navigate("selectTaskModel/${userId}")
                    }
                ),
            )

            SelectStudentActionScreen(
                onPressHome = { /*TODO*/ },
                onNavigateBack = {
                    navController.popBackStack()
                },
                menuActions = menuActions,
                userId = userId
            )

        }

        composable(route = "selectTaskModel/{userId}",
            arguments = listOf(navArgument("userId") {type = NavType.IntType})
        ) { navBackStackEntry ->
            val userId = navBackStackEntry.arguments?.getInt("userId")!!

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
                    navController.navigate("setDateAndRewardInfo")
                },
                onCreateTask = {
                    navController.navigate("selectTaskType/${userId}")
                },
                selectTaskModelScreenViewModel = viewModel,

            )

        }

        composable(route = "selectTaskType/{userId}",
            arguments = listOf(navArgument("userId") {type = NavType.IntType})
        ){ navBackStackEntry ->

            val userId = navBackStackEntry.arguments?.getInt("userId")!!
            MakeTaskScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxSize(),
                userId = userId
            )
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

        composable(route = "setDateAndRewardInfo") { navBackStackEntry ->
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
                        modifier = Modifier.align(Alignment.Center)
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

private data class MenuActionStudent(
    val displayName: String,
    val displayImage: ImageContent?,
    val onClick: () -> Unit
)

// 2
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SelectStudentActionScreen(
    onPressHome: () -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    menuActions: List<MenuActionStudent>,
    userId: Int,
    selectStudentScreenViewModel: SelectStudentScreenViewModel = hiltViewModel()

) {


    Scaffold(
        topBar = {
            TeacherCenterAlignedTopAppBar(
                title = "Selección de Acción del estudiante ${userId}",
                onNavigateBack = onNavigateBack,
                onPressHome = { /*TODO*/ },
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            // TODO: Si únicamente estarán las acciones seleccionables, quitar la column
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(menuActions) { menuAction ->
                    Button(
                        onClick = menuAction.onClick,
                        modifier = Modifier.height(176.dp),
                        shape = RoundedCornerShape(16.dp),
                        contentPadding = PaddingValues(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer,
                            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    ) {
                        val hasDisplayImage = menuAction.displayImage != null

                        Column(
                            verticalArrangement = Arrangement.SpaceBetween,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            if (hasDisplayImage) {
                                DynamicImage(
                                    image = menuAction.displayImage!!,
                                    modifier = Modifier
                                        .padding(bottom = 12.dp)
                                        .weight(1f)
                                )
                            }

                            Text(
                                text = menuAction.displayName,
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.titleMedium
                                    .copy(
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 20.sp
                                    )
                            )
                        }
                    }
                }
            }
        }

    }
}



// 3
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

    val taskModelListUIState = selectTaskModelScreenViewModel.uiStateTasKModelList

    LaunchedEffect(Unit){
       // selectTaskModelScreenViewModel.loadTaskModels()
        
    }

/*
    if (taskModelListUIState == ListTaskModelUIState.Loading)
    {
        CircularProgressIndicator(modifier = Modifier.fillMaxSize())
        return
    }

 */

    val numberList = List(4){
        0
    }
    
    Scaffold (
        modifier  = modifier,
        topBar = {
            TeacherCenterAlignedTopAppBar(
                title = "Personalización de plantilla del alumno",
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
                /*itemsIndexed(
                    (taskModelListUIState as ListTaskModelUIState.ListTaskModelIdsReady).listTaskModelIds)

                 */
                items(numberList)
                {/*index, item ->*/
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally, // Alineación horizontal centrada
                        verticalArrangement = Arrangement.Center, // Alineación vertical centrada
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier
                                .requiredSize(150.dp)
                                .background(Color.Gray)
                                .clickable(onClick = onTaskModelSelect)
                        ) {
                            // Contenido del cuadrado (Plantillas de tareas)
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            onClick = {
                                //selectTaskModelScreenViewModel.updateSelectTaskModel(index)
                                onCustomModelSelect()
                            },
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

// 4
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SetTaskInfoScreen(
    onPressHome: () -> Unit,
    onNavigateBack: () -> Unit,
    onContinue: () -> Unit,
    modifier: Modifier = Modifier,
    selectTaskModelScreenViewModel: SelectTaskModelScreenViewModel

) {
    LaunchedEffect(Unit) {

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
                value = selectTaskModelScreenViewModel.tittle,
                onValueChange = {
                        selectTaskModelScreenViewModel.onTittleChange(it)
                },
                singleLine = true
            )
            Text("Descripción: ",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(width = 20.dp, height = 100.dp),
                value = selectTaskModelScreenViewModel.description,
                onValueChange = {
                        selectTaskModelScreenViewModel.onDescriptionChange(it)
                },
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

// 5
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
                value = selectTaskModelScreenViewModel.startDate,
                onValueChange = {
                        selectTaskModelScreenViewModel.onStartDateChange(it)
                },
                singleLine = true
            )

            Text("Fecha finalización: ",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = selectTaskModelScreenViewModel.dueDate,
                onValueChange = {
                        selectTaskModelScreenViewModel.onDueDateChange(it)
                },
                singleLine = true
            )

            Text("Premio:",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = selectTaskModelScreenViewModel.reward.toString(),
                onValueChange = {
                        selectTaskModelScreenViewModel.onRewardChange(it.toInt())
                },
                singleLine = true
            )
            Button(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = onStudentListReturn,
            ) {
                Text(text = "Guardar",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium))
            }

        }

    }

}

