package com.integrame.app.teacher.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.integrame.app.core.data.model.content.ImageContent
import com.integrame.app.core.ui.components.DynamicImage
import com.integrame.app.core.ui.components.appbar.TeacherCenterAlignedTopAppBar
import com.integrame.app.teacher.ui.viewmodel.MakeTaskScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MakeTaskScreen(
    onNavigateBack: () -> Unit,
    modifier: Modifier,
    makeTaskScreenViewModel: MakeTaskScreenViewModel = hiltViewModel(),
    userId: Int
){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "selectTaskType",
        modifier = modifier

    ) {

        composable(route = "selectTaskType") {
            val menuActions = listOf(
                MenuActionTaskType(
                    displayName = "Genérica",
                    displayImage = null,
                    onClick = {
                        navController.navigate("makeTaskGenericMenu")
                    }
                ),
                MenuActionTaskType(
                    displayName = "Materiales",
                    displayImage = null,
                    onClick = {
                        navController.navigate("makeTaskMaterial")
                    }
                )
            )
            SelectTaskTypeScreen(
                onPressHome = { /*TODO*/ },
                onNavigateBack = onNavigateBack,
                menuActions = menuActions
            )
        }

        composable(route = "makeTaskGenericMenu") {
            NewGenericTaskScreen(modifier =  Modifier.fillMaxSize(),
                onNewStep = {
                    navController.navigate("newStep")
                },
                onNavigateBack = {
                     navController.popBackStack()
                },
                makeTaskScreenViewModel = makeTaskScreenViewModel,
                userId = userId

            )
        }

        composable(route = "newStep"){
            NewGenericStepTaskScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxSize(),
                onNewTaskBack = {
                    navController.popBackStack()
                },
                makeTaskScreenViewModel = makeTaskScreenViewModel
            )

        }

        composable(route = "makeTaskMaterial") {
            NewMaterialTaskScreen(
                modifier = modifier,
                onCreateMaterial = {
                                   navController.navigate("createNewMaterial")
                },
                onNavigateBack = {
                                 navController.popBackStack()
                },
                makeTaskScreenViewModel = makeTaskScreenViewModel,
                userId = userId
            )
        }

        composable(route = "createNewMaterial") {
            CreateMaterialScreen(
                onNavigateBack = {
                     navController.popBackStack()
                },
                onNewTaskBack = {
                    navController.popBackStack()
                },
                makeTaskScreenViewModel = makeTaskScreenViewModel
            )
        }



    }

}

private data class MenuActionTaskType(
    val displayName: String,
    val displayImage: ImageContent?,
    val onClick: () -> Unit
)

// 2
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SelectTaskTypeScreen(
    onPressHome: () -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    menuActions: List<MenuActionTaskType>,

) {

    Scaffold(
        topBar = {
            // CenterAling
            TeacherCenterAlignedTopAppBar(
                title = "Selección del tipo de tarea",
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NewGenericTaskScreen(
    modifier: Modifier,
    onNewStep: () -> Unit,
    onNavigateBack: () -> Unit,
    makeTaskScreenViewModel: MakeTaskScreenViewModel,
    userId: Int
) {

    val makeTaskUIState = makeTaskScreenViewModel.makeTaskUIState

    LaunchedEffect(Unit) {

    }

    /*
    if (makeTaskUIState is MakeTaskUIState.Loading)
    {
        CircularProgressIndicator(modifier = Modifier.fillMaxSize())
        return

    }
     */

    val listaNumeros = List(9) {
        0
    }

    makeTaskScreenViewModel.setStudentId(userId)

    Scaffold(
        modifier = modifier,
        topBar = {
            TeacherCenterAlignedTopAppBar(
                title = "Creación de tarea genérica",
                onNavigateBack = onNavigateBack,
                onPressHome = { /*TODO*/ })

        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.Start
        ) {
            item {
                Text(
                    "Nombre tarea: ",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium)
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = makeTaskScreenViewModel.genericTaskTittle,
                    onValueChange = { makeTaskScreenViewModel.onGenericTittleChange(it) },
                    singleLine = true
                )

                Text(
                    "Descripción: ",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium)
                )
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(width = 20.dp, height = 200.dp),
                    value = makeTaskScreenViewModel.genericTaskDescription,
                    onValueChange = { makeTaskScreenViewModel.onGenericDescriptionChange(it) },
                    singleLine = true
                )

                Text(
                    "Fecha inicio: ",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium)
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = makeTaskScreenViewModel.genericTaskStartDate,
                    onValueChange = { makeTaskScreenViewModel.GenericStartDateChange(it) },
                    singleLine = true
                )

                Text(
                    "Fecha finalización",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium)
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = makeTaskScreenViewModel.genericTaskDueDate,
                    onValueChange = { makeTaskScreenViewModel.onGenericDueDateChange(it) },
                    singleLine = true
                )

                Text(
                    "Pasos",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium)
                )

                Box(modifier = Modifier) {
                    Button(
                        modifier = Modifier.align(Alignment.CenterEnd),
                        onClick = onNewStep,
                    ) {
                        Text(
                            text = "+",
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium)
                        )
                    }

                }
            }

            items(1){
                Box(modifier = Modifier
                    .requiredSize(92.dp)
                    .background(Color.Gray)
                    .clickable {

                    }
                ) {}
            }

            item {

                Box(modifier = Modifier) {
                    Button(
                        modifier = Modifier.align(Alignment.CenterEnd),
                        onClick = {
                              makeTaskScreenViewModel.createGenericTask()

                        },
                    ) {
                        Text(
                            text = "Terminar tarea",
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium)
                        )
                    }

                }

            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NewGenericStepTaskScreen(
    onNavigateBack: () -> Unit,
    onNewTaskBack: () -> Unit,
    modifier: Modifier = Modifier,
    makeTaskScreenViewModel: MakeTaskScreenViewModel
    ) {

        LaunchedEffect(Unit) {

        }

        Scaffold(
            modifier = modifier,
            topBar = {
                TeacherCenterAlignedTopAppBar(
                    title = "Añadir nuevo paso",
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
                Text(
                    "Titulo: ",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium)
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = makeTaskScreenViewModel.genericTaskStepTittle,
                    onValueChange = {
                        makeTaskScreenViewModel.onGenericStepTittleChange(it)
                    },
                    singleLine = true
                )
                Text(
                    "Descripción: ",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium)
                )
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(width = 20.dp, height = 200.dp),
                    value = makeTaskScreenViewModel.genericTaskStepDescription,
                    onValueChange = {
                        makeTaskScreenViewModel.onGenericDescriptionStepChange(it)
                    },
                )

                Text(
                    "Audio: ",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium)
                )
                Button(
                    modifier = Modifier.align(Alignment.End),
                    onClick = { /*TODO*/ },
                ) {
                    Text(
                        text = "+",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium)
                    )
                }

                Text(
                    "Imagen:",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium)
                )
                Button(
                    modifier = Modifier.align(Alignment.End),
                    onClick = { /*TODO*/ },
                ) {
                    Text(
                        text = "+",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium)
                    )
                }
                Text(
                    "Video: ",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium)
                )
                Button(
                    modifier = Modifier.align(Alignment.End),
                    onClick = {
                          //makeTaskScreenViewModel.addStep()
                    },
                ) {
                    Text(
                        text = "+",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium)
                    )
                }

                Button(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onClick = onNewTaskBack,
                ) {
                    Text(
                        text = "Añadir Paso",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium)
                    )
                }
            }

        }

    }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NewMaterialTaskScreen(
    modifier: Modifier,
    onCreateMaterial: () -> Unit,
    onNavigateBack: () -> Unit,
    makeTaskScreenViewModel: MakeTaskScreenViewModel,
    userId: Int
) {

    val makeTaskUIState = makeTaskScreenViewModel.makeTaskUIState

    LaunchedEffect(Unit) {

    }

    /*
    if (makeTaskUIState is MakeTaskUIState.Loading)
    {
        CircularProgressIndicator(modifier = Modifier.fillMaxSize())
        return

    }
     */

    val listaNumeros = List(9) {
        0
    }

    makeTaskScreenViewModel.setStudentId(userId)

    Scaffold(
        modifier = modifier,
        topBar = {
            TeacherCenterAlignedTopAppBar(
                title = "Creación de tarea material",
                onNavigateBack = onNavigateBack,
                onPressHome = { /*TODO*/ })

        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.Start
        ) {
            item {
                Text(
                    "Nombre tarea: ",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium)
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = makeTaskScreenViewModel.materialTaskTittle,
                    onValueChange = { makeTaskScreenViewModel.onGenericTittleChange(it) },
                    singleLine = true
                )

                Text(
                    "Descripción: ",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium)
                )
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(width = 20.dp, height = 200.dp),
                    value = makeTaskScreenViewModel.materialTaskDescription,
                    onValueChange = { makeTaskScreenViewModel.onGenericDescriptionChange(it) },
                    singleLine = true
                )

                Text(
                    "Fecha inicio: ",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium)
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = makeTaskScreenViewModel.materialTaskstartDate,
                    onValueChange = { makeTaskScreenViewModel.GenericStartDateChange(it) },
                    singleLine = true
                )

                Text(
                    "Fecha finalización",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium)
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = makeTaskScreenViewModel.materialTaskDueDate,
                    onValueChange = { makeTaskScreenViewModel.onGenericDueDateChange(it) },
                    singleLine = true
                )

                Text(
                    "Materiales",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium)
                )

                Box(modifier = Modifier) {
                    Button(
                        modifier = Modifier.align(Alignment.CenterEnd),
                        onClick = onCreateMaterial ,
                    ) {
                        Text(
                            text = "+",
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium)
                        )
                    }

                }
            }

            items(1){
                Box(modifier = Modifier
                    .requiredSize(92.dp)
                    .background(Color.Gray)
                    .clickable {

                    }
                ) {}
            }

            item {

                Box(modifier = Modifier) {
                    Button(
                        modifier = Modifier.align(Alignment.CenterEnd),
                        onClick = {
                            makeTaskScreenViewModel.createGenericTask()

                        },
                    ) {
                        Text(
                            text = "Terminar tarea",
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium)
                        )
                    }

                }

            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CreateMaterialScreen(
    onNavigateBack: () -> Unit,
    onNewTaskBack: () -> Unit,
    modifier: Modifier = Modifier,
    makeTaskScreenViewModel: MakeTaskScreenViewModel
) {

    LaunchedEffect(Unit) {

    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TeacherCenterAlignedTopAppBar(
                title = "Añadir nuevo material",
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
            Text(
                "Nombre del material: ",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium)
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = makeTaskScreenViewModel.materialName,
                onValueChange = {
                                makeTaskScreenViewModel.onMaterialNameChange(it)

                },
                singleLine = true
            )

            Text(
                "Cantidad: ",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium)
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = makeTaskScreenViewModel.materialAmount.toString(),
                onValueChange = {
                                makeTaskScreenViewModel.onMaterialAmountChange(it.toInt())

                },
                singleLine = true
            )

            Text(
                "Nombre propiedad: ",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium)
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = makeTaskScreenViewModel.materialPropertyName,
                onValueChange = {
                                makeTaskScreenViewModel.onMaterialPropertyNameChange(it)

                },
                singleLine = true
            )

            Text(
                "Imagen de la propiedad:",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium)
            )
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
                onClick = onNewTaskBack,
            ) {
                Text(
                    text = "Añadir Material",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium)
                )
            }
        }

    }

}



