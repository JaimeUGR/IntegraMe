package com.integrame.app.tasks.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.integrame.app.R
import com.integrame.app.core.data.fake.FakeResources
import com.integrame.app.core.ui.components.appbar.StudentTaskTopAppBar
import com.integrame.app.tasks.data.model.MenuTaskModel
import com.integrame.app.core.data.model.content.ContentProfile
import com.integrame.app.core.ui.components.DynamicImage
import com.integrame.app.tasks.ui.viewmodel.ClassroomListUIState
import com.integrame.app.tasks.ui.viewmodel.MenuTaskScreenViewModel
import com.integrame.app.tasks.ui.viewmodel.SelectMenuUIState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuTaskScreen(
    taskModel: MenuTaskModel,
    contentProfile: ContentProfile,
    onNavigateBack: () -> Unit,
    onPressHome: () -> Unit,
    onPressChat: () -> Unit,
    modifier: Modifier = Modifier,
    menuTaskViewModel: MenuTaskScreenViewModel = hiltViewModel()

) {
    val navController = rememberNavController()

    /*
        1) Programar onNavigateBack
        2) Parametro para el aula seleccionada
        3) Método para navegar a la pantalla del aula seleccionada
     */

    NavHost(
        navController = navController,
        startDestination = "classrooms",
        modifier = modifier
    ) {
        composable(route = "classrooms") {
            SelectClassroomScreen(
                taskModel = taskModel,
                contentProfile = contentProfile,
                onNavigateBack = onNavigateBack,
                onPressHome = { /*TODO*/ },
                onPressChat = { /*TODO*/ },
                modifier = Modifier.fillMaxSize(),
                menuTaskViewModel = menuTaskViewModel,
                onClassroomSelected = {
                  navController.navigate("menus")
                }
            )
        }

        composable(route = "menus") {
            SelectMenuScreen(
                taskModel = taskModel,
                contentProfile = contentProfile,
                onNavigateBack = {
                                 navController.popBackStack()
                },
                onPressHome = { /*TODO*/ },
                onPressChat = { /*TODO*/ },
                modifier = Modifier.fillMaxSize(),
                menuTaskViewModel = menuTaskViewModel
            )
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SelectClassroomScreen(
    taskModel: MenuTaskModel,
    contentProfile: ContentProfile,
    onNavigateBack: () -> Unit,
    onPressHome: () -> Unit,
    onPressChat: () -> Unit,
    onClassroomSelected: () -> Unit,
    modifier: Modifier = Modifier,
    menuTaskViewModel: MenuTaskScreenViewModel = hiltViewModel()
) {

    var numClassroom = 0
    var padding = 10.dp

    // Observar los cambios en el estado del ViewModel
    val classroomListUIState = menuTaskViewModel.uiStateClassroomList

    LaunchedEffect(Unit) {
        menuTaskViewModel.loadClassroomsIds(taskModel)
    }

    if (classroomListUIState == ClassroomListUIState.Loading)
    {
        CircularProgressIndicator(modifier = Modifier.fillMaxSize())
        return
    }

    Scaffold(
        modifier = modifier
            .background(MaterialTheme.colorScheme.primaryContainer),

        topBar = {
            StudentTaskTopAppBar(
                title = taskModel.displayName,
                onNavigateBack = onNavigateBack,
                onPressHome = onPressHome,
                onPressChat = onPressChat
            )
        }
    ) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(1),
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
        ) {
            itemsIndexed(
                (classroomListUIState as ClassroomListUIState.ListLoaded).classroomsIds
            ) { index, item ->
                Button(
                    modifier = Modifier
                        .height(130.dp)
                        .padding(all = 10.dp),
                    onClick = {
                        numClassroom = index;
                        menuTaskViewModel.updateSelectClassroom(numClassroom)
                        onClassroomSelected()
                    },
                    shape = RoundedCornerShape(26.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .height(75.dp)
                            .padding(all = padding),
                        text = "La clase ${numClassroom}",
                        fontSize = MaterialTheme.typography.displaySmall.fontSize
                    )
                    DynamicImage(
                        image = FakeResources.remoteImages[0],
                        modifier = Modifier
                            .size(100.dp)
                            .padding(all = padding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SelectMenuScreen(
    taskModel: MenuTaskModel,
    contentProfile: ContentProfile,
    onNavigateBack: () -> Unit,
    onPressHome: () -> Unit,
    onPressChat: () -> Unit,
    modifier: Modifier = Modifier,
    menuTaskViewModel: MenuTaskScreenViewModel = hiltViewModel()
) {

    val selectMenuUIState = menuTaskViewModel.uiStateSelectMenu

    LaunchedEffect(Unit) {
        menuTaskViewModel.loadClassroomsIds(taskModel)
        menuTaskViewModel.loadClassroomsMenus(taskModel)
    }

    if (selectMenuUIState == SelectMenuUIState.Loading)
    {
        CircularProgressIndicator(modifier = Modifier.fillMaxSize())
        return
    }

    var numMenu = menuTaskViewModel.numMenu

    var padding = 10.dp

    Scaffold(
        modifier = modifier
            .background(MaterialTheme.colorScheme.primaryContainer),

        topBar = {
            StudentTaskTopAppBar(
                title = taskModel.displayName,
                onNavigateBack = onNavigateBack,
                onPressHome = onPressHome,
                onPressChat = onPressChat
            )
        }
    ) { innerPadding ->

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth()

                ) {

                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .height(75.dp)
                            .padding(all = padding),
                        text = "La clase " + menuTaskViewModel.selectClassroom,
                        fontSize = MaterialTheme.typography.displaySmall.fontSize
                    )

                    DynamicImage(
                        image = FakeResources.remoteImages[0],
                        modifier = Modifier
                            .size(100.dp)
                            .padding(all = padding)
                    )

                }

                Row(
                    modifier = Modifier
                ) {
                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .height(120.dp)
                            .padding(all = padding),

                        onClick = {
                            if (numMenu != 0) {
                                numMenu--;
                                //menuTaskScreenViewModel.updateRememberAmount(menuTaskScreenViewModel.classroomMenus[numMenu].requestedAmount)
                                println("numMenuActual: " + numMenu);
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Retroceder al menu anterior",
                            modifier = Modifier.size(60.dp)
                        )
                    }
                    DynamicImage(
                        image = (selectMenuUIState as SelectMenuUIState.ListLoaded).classroomMenus[numMenu].image,
                        modifier = Modifier
                            .weight(1f)
                            .height(120.dp)
                            .padding(all = padding),
                    )
                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .height(120.dp)
                            .padding(all = padding),
                        onClick = {
                            if (numMenu + 1 != (selectMenuUIState as SelectMenuUIState.ListLoaded).classroomMenus.size ) {
                                numMenu++;
                                menuTaskViewModel.updateRememberAmount((selectMenuUIState as SelectMenuUIState.ListLoaded).classroomMenus[numMenu].requestedAmount)
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowForward,
                            contentDescription = "Avanzar al menu siguiente",
                            modifier = Modifier.size(60.dp)
                        )
                    }

                }

                val listaRecursos = listOf(
                    R.drawable.cero,
                    R.drawable.uno,
                    R.drawable.dos,
                    R.drawable.tres,
                    R.drawable.cuatro,
                    R.drawable.cinco,
                    R.drawable.seis,
                    R.drawable.siete,
                    R.drawable.ocho
                )
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                        .padding(16.dp),
                ) {
                    itemsIndexed(listaRecursos) { index, item ->
                        Button(
                            modifier = Modifier
                                .weight(1f)
                                .height(130.dp)
                                .padding(all = 2.dp),
                            onClick = {
                                //Se debe de actualizar la cantidadc que queremos de ese menu
                                //task.classroomMenus[numClaseActual.toInt()].menuOptions[numMenuActual].setRequestedAmount(index);
                                menuTaskViewModel.updateRequestedAmount(numMenu, index)
                                menuTaskViewModel.rememberAmount = index;
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor =
                                if(menuTaskViewModel.rememberAmount == index){
                                    Color.Green
                                }else{
                                    Color.Gray
                                }
                            ),
                            shape = CutCornerShape(8.dp)
                        ){
                            Image(
                                painter = painterResource(id = listaRecursos[index]),
                                contentDescription = "mano con ${index} dedos",
                            )
                        }
                    }
                }
            }
        }
    }
}