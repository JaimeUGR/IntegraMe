package com.integrame.app.tasks.ui.screens

import android.annotation.SuppressLint
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.integrame.app.R
import com.integrame.app.core.data.fake.FakeResources
import com.integrame.app.core.ui.components.DynamicImage
import com.integrame.app.core.ui.components.appbar.StudentTaskTopAppBar
import com.integrame.app.tasks.data.model.MenuTask

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun MenuTaskScreen(
    modifier: Modifier = Modifier,
    task: MenuTask,
    onNavigateBack: () -> Unit,
    onPressHome: () -> Unit,
    onPressChat: () -> Unit
) {

    var numMenu by remember { mutableStateOf(0) }
    var numClassroom by remember { mutableStateOf(0) }
    var requestAmount by remember { mutableStateOf(0) }
    var classroomLetter = "A"
    var padding = 10.dp

    Scaffold(
        modifier = modifier
            .background(MaterialTheme.colorScheme.primaryContainer),

        topBar = {
            StudentTaskTopAppBar(
                title = task.displayName,
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
                        text = "La clase " + classroomLetter,
                        fontSize = MaterialTheme.typography.displaySmall.fontSize
                    )
                    Image(
                        painter = painterResource(id = R.drawable.clase),
                        contentDescription = "Imagen de una clase",
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
                                requestAmount = task.classroomMenus[numClassroom.toInt()].menuOptions[numMenu].requestedAmount;
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
                        image = task.classroomMenus[numMenu].menuOptions[0].image,
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
                            if (numMenu + 1 != task.classroomMenus[numClassroom.toInt()].menuOptions.size) {
                                numMenu++;
                                requestAmount = task.classroomMenus[numClassroom.toInt()].menuOptions[numMenu].requestedAmount;

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
                                requestAmount = index;
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor =
                                if(requestAmount == index){
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
@Preview (showSystemUi = true)
@Composable
fun MenuTaskScreenPreview() {
    MenuTaskScreen(
        task = FakeResources.menuTasks[5],
        onNavigateBack = { /*TODO*/ },
        onPressHome = { /*TODO*/ },
        onPressChat = { /*TODO*/ },
        modifier = Modifier.fillMaxSize()
    )
}