package com.integrame.app.tasks.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.integrame.app.R
import com.integrame.app.core.ui.components.appbar.StudentTaskTopAppBar
import com.integrame.app.tasks.data.model.MenuTaskModel
import com.integrame.app.core.data.model.content.ContentProfile
import com.integrame.app.core.data.model.content.LocalImage
import com.integrame.app.core.ui.components.DynamicImage
import com.integrame.app.tasks.data.model.Classroom
import com.integrame.app.tasks.data.model.ClassroomMenu
import com.integrame.app.tasks.data.model.MenuOption
import com.integrame.app.tasks.ui.viewmodel.MenuTaskUIState
import com.integrame.app.tasks.ui.viewmodel.MenuTaskScreenViewModel

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
    // Observar los cambios en el estado del ViewModel
    val classroomListUIState = menuTaskViewModel.uiStateClassroomList

    LaunchedEffect(Unit) {
        menuTaskViewModel.loadTaskModel(taskModel)
    }

    Scaffold(
        topBar = {
            var onNavigateBack = onNavigateBack

            if (menuTaskViewModel.uiStateClassroomList is MenuTaskUIState.SelectMenus)
                onNavigateBack = { menuTaskViewModel.displayClassroomList() }

            StudentTaskTopAppBar(
                title = taskModel.displayName,
                onNavigateBack = onNavigateBack,
                onPressHome = onPressHome,
                onPressChat = onPressChat
            )
        },
        modifier = modifier
    ) { innerPadding ->
        when (classroomListUIState) {
            is MenuTaskUIState.Loading -> {
                Box(
                    modifier = Modifier.padding(innerPadding).fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier.size(80.dp))
                }
            }
            is MenuTaskUIState.SelectClassroom -> {
                LazyColumn(
                    modifier = Modifier.padding(innerPadding).fillMaxSize().padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    userScrollEnabled = false
                ) {
                    items(taskModel.classrooms) { classroom ->
                        ClassroomButton(
                            classroom = classroom,
                            onClick = { menuTaskViewModel.selectClassroom(classroom.classroomId) },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
            is MenuTaskUIState.SelectMenus -> {
                MenuScreen(
                    classroomListUIState,
                    onUpdateMenuAmount = { menuIndex, amount ->
                        menuTaskViewModel.updateMenuAmount(
                            menuOptionIndex = menuIndex,
                            amount = amount
                        )
                    },
                    modifier = Modifier.padding(innerPadding).fillMaxSize().padding(16.dp)
                )
            }
        }
    }
}

@Composable
private fun ClassroomButton(
    classroom: Classroom,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        contentPadding = PaddingValues(12.dp),
        shape = RectangleShape,
        border = BorderStroke(2.dp, MaterialTheme.colorScheme.secondary),
        colors = ButtonDefaults.buttonColors(containerColor =  MaterialTheme.colorScheme.secondaryContainer)
    ) {
        DynamicImage(
            image = classroom.displayImage,
            modifier = Modifier.width(112.dp)
        )

        Text(
            text = classroom.displayText.text,
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
    }
}

@Composable
private fun ClassroomMenuSummary(
    modifier: Modifier = Modifier
) {

}

private val AMOUNT_IMAGES = listOf(
    LocalImage(R.drawable.cero, "Imagen cantidad cero"),
    LocalImage(R.drawable.uno, "Imagen cantidad uno"),
    LocalImage(R.drawable.dos, "Imagen cantidad dos"),
    LocalImage(R.drawable.tres, "Imagen cantidad tres"),
    LocalImage(R.drawable.cuatro, "Imagen cantidad cuatro"),
    LocalImage(R.drawable.cinco, "Imagen cantidad cinco"),
    LocalImage(R.drawable.seis, "Imagen cantidad seis"),
    LocalImage(R.drawable.siete, "Imagen cantidad siete"),
    LocalImage(R.drawable.ocho, "Imagen cantidad ocho")
)

@Composable
private fun MenuScreen(
    menuScreenUIState: MenuTaskUIState.SelectMenus,
    onUpdateMenuAmount: (Int, Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val classroomMenu = menuScreenUIState.classroomMenu
    val selectedMenuIndex = menuScreenUIState.selectedMenuIndex

    val classroom = classroomMenu.classroom
    val menus = classroomMenu.menuOptions

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
        ) {
            DynamicImage(
                image = classroom.displayImage,
                modifier = Modifier
                    .size(100.dp)
                    .padding(all = 8.dp)
            )

            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(all = 16.dp),
                text = classroom.displayText.text,
                fontSize = MaterialTheme.typography.displaySmall.fontSize
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Botón de retroceder
            Button(
                modifier = Modifier
                    .weight(1f)
                    .height(120.dp)
                    .padding(8.dp),
                onClick = { menuScreenUIState.previousMenuOption() },
                enabled = selectedMenuIndex != 0
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Retroceder al menu anterior",
                    modifier = Modifier.size(60.dp)
                )
            }

            // Imagen del menú
            DynamicImage(
                image = menus[selectedMenuIndex].displayImage,
                modifier = Modifier
                    .weight(1f)
                    .height(120.dp)
                    .padding(8.dp)
            )

            // Botón de avanzar
            Button(
                modifier = Modifier
                    .weight(1f)
                    .height(120.dp)
                    .padding(8.dp),
                onClick = { menuScreenUIState.nextMenuOption() },
                enabled = selectedMenuIndex < menus.size - 1
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = "Avanzar al menu siguiente",
                    modifier = Modifier.size(60.dp)
                )
            }
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            userScrollEnabled = false
        ) {
            // Amount es el índice que se corresponde con la cantidad que sale en la foto
            itemsIndexed(AMOUNT_IMAGES) { amount, amountImage ->
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .height(130.dp)
                        .padding(all = 2.dp),
                    onClick = {
                        onUpdateMenuAmount(selectedMenuIndex, amount)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (amount == menuScreenUIState.selectedMenuAmount) Color.Green else Color.Gray
                    ),
                    shape = CutCornerShape(8.dp)
                ){
                    DynamicImage(
                        image = amountImage
                    )
                }
            }
        }
    }
}