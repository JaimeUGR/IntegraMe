package com.integrame.app.tasks.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.integrame.app.core.ui.components.DynamicImage
import com.integrame.app.core.ui.components.PaginatedBottomAppBar
import com.integrame.app.login.ui.viewmodel.StudentLoginUIState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DinningCommandScreen(
    modifier: Modifier = Modifier,
) {
    val imageUrl = "https://arasaac.org/pictograms/es/35693/0"

    Scaffold (
        modifier = modifier
            .background(MaterialTheme.colorScheme.primaryContainer),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Menú \ncomedor"
                    )
                }
            )

        }

    ){innerPadding ->


        val separacion = 10.dp
        val alto = 120.dp
        var letraDeLaClase = "A"

        Row (
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
                        .align(Alignment.End)
                        .width(100.dp)

                ) {

                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .height(alto)
                            .padding(
                                start = separacion,
                                end = separacion,
                                top = separacion,
                                bottom = separacion
                            ),
                        text = "La clase " + letraDeLaClase
                    )
                }
                Row(
                    modifier = Modifier

                ) {
                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .height(alto)
                            .padding(
                                start = separacion,
                                end = separacion,
                                top = separacion,
                                bottom = separacion
                            ),

                        onClick = { /*TODO*/ }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Retroceder al menu anterior",
                            modifier = Modifier.size(60.dp)
                        )
                    }
                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .height(alto)
                            .padding(
                                start = separacion,
                                end = separacion,
                                top = separacion,
                                bottom = separacion
                            ),
                        text = "IMAGEN TIPO DE MENU"
                    )
                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .height(alto)
                            .padding(
                                start = separacion,
                                end = separacion,
                                top = separacion,
                                bottom = separacion
                            ),
                        onClick = { /*TODO*/ }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowForward,
                            contentDescription = "Avanzar al menu siguiente",
                            modifier = Modifier.size(60.dp)
                        )
                    }
                }
                Row {
                    AsyncImage(
                        modifier = Modifier
                            .weight(1f)
                            .height(alto)
                            .padding(
                                start = separacion,
                                end = separacion,
                                top = separacion,
                                bottom = separacion
                            ),
                        model = "https://arasaac.org/pictograms/es/35693/0",
                        contentDescription = "Imagen mano con 0 dedos"
                    )
                    AsyncImage(
                        modifier = Modifier
                            .weight(1f)
                            .height(alto)
                            .padding(
                                start = separacion,
                                end = separacion,
                                top = separacion,
                                bottom = separacion
                            ),
                        model = "https://arasaac.org/pictograms/es/35677/uno",
                        contentDescription = "Imagen mano con 1 dedos"
                    )
                    AsyncImage(
                        modifier = Modifier
                            .weight(1f)
                            .height(alto)
                            .padding(
                                start = separacion,
                                end = separacion,
                                top = separacion,
                                bottom = separacion
                            ),
                        model = "https://arasaac.org/pictograms/es/35679/2",
                        contentDescription = "Imagen mano con 2 dedos"
                    )
                }
                Row {
                    AsyncImage(
                        modifier = Modifier
                            .weight(1f)
                            .height(alto)
                            .padding(
                                start = separacion,
                                end = separacion,
                                top = separacion,
                                bottom = separacion
                            ),
                        model = "https://arasaac.org/pictograms/es/35681/3",
                        contentDescription = "Imagen mano con 3 dedos"
                    )
                    AsyncImage(
                        modifier = Modifier
                            .weight(1f)
                            .height(alto)
                            .padding(
                                start = separacion,
                                end = separacion,
                                top = separacion,
                                bottom = separacion
                            ),
                        model = "https://arasaac.org/pictograms/es/35665/4",
                        contentDescription = "Imagen mano con 4 dedos"
                    )
                    AsyncImage(
                        modifier = Modifier
                            .weight(1f)
                            .height(alto)
                            .padding(
                                start = separacion,
                                end = separacion,
                                top = separacion,
                                bottom = separacion
                            ),
                        model = "https://arasaac.org/pictograms/es/35631/5",
                        contentDescription = "Imagen mano con 5 dedos"
                    )
                }
                Row {
                    AsyncImage(
                        modifier = Modifier
                            .weight(1f)
                            .height(alto)
                            .padding(
                                start = separacion,
                                end = separacion,
                                top = separacion,
                                bottom = separacion
                            ),
                        model = "https://arasaac.org/pictograms/es/35683/6",
                        contentDescription = "Imagen mano con 6 dedos"
                    )
                    AsyncImage(
                        modifier = Modifier
                            .weight(1f)
                            .height(alto)
                            .padding(
                                start = separacion,
                                end = separacion,
                                top = separacion,
                                bottom = separacion
                            ),
                        model = "https://arasaac.org/pictograms/es/35685/7",
                        contentDescription = "Imagen mano con 7 dedos"
                    )
                    AsyncImage(
                        modifier = Modifier
                            .weight(1f)
                            .height(alto)
                            .padding(
                                start = separacion,
                                end = separacion,
                                top = separacion,
                                bottom = separacion
                            ),
                        model = "https://arasaac.org/pictograms/es/35687/8",
                        contentDescription = "Imagen mano con 8 dedos"
                    )
                }
            }



            /*
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier

                ) {
                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .height(alto)
                            .padding(
                                start = separacion,
                                end = separacion,
                                top = separacion,
                                bottom = separacion
                            ),

                        onClick = { /*TODO*/ }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Retroceder al menu anterior",
                            modifier = Modifier.size(60.dp)
                        )
                    }
                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .height(alto)
                            .padding(
                                start = separacion,
                                end = separacion,
                                top = separacion,
                                bottom = separacion
                            ),
                        text = "Imagen0"
                    )
                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .height(alto)
                            .padding(
                                start = separacion,
                                end = separacion,
                                top = separacion,
                                bottom = separacion
                            ),
                        onClick = { /*TODO*/ }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowForward,
                            contentDescription = "Avanzar al menu siguiente",
                            modifier = Modifier.size(60.dp)
                        )
                    }
                }
                Row {
                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .height(alto)
                            .padding(
                                start = separacion,
                                end = separacion,
                                top = separacion,
                                bottom = separacion
                            ),
                        text = "Imagen0"
                    )
                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .height(alto)
                            .padding(
                                start = separacion,
                                end = separacion,
                                top = separacion,
                                bottom = separacion
                            ),
                        text = "Imagen0"
                    )
                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .height(alto)
                            .padding(
                                start = separacion,
                                end = separacion,
                                top = separacion,
                                bottom = separacion
                            ),
                        text = "Imagen0"
                    )
                }

                Row {
                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .height(alto)
                            .padding(
                                start = separacion,
                                end = separacion,
                                top = separacion,
                                bottom = separacion
                            ),
                        text = "Imagen0"
                    )
                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .height(alto)
                            .padding(
                                start = separacion,
                                end = separacion,
                                top = separacion,
                                bottom = separacion
                            ),
                        text = "Imagen0"
                    )
                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .height(alto)
                            .padding(
                                start = separacion,
                                end = separacion,
                                top = separacion,
                                bottom = separacion
                            ),
                        text = "Imagen0"
                    )
                }
                Row {
                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .height(alto)
                            .padding(
                                start = separacion,
                                end = separacion,
                                top = separacion,
                                bottom = separacion
                            ),
                        text = "Imagen0"
                    )
                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .height(alto)
                            .padding(
                                start = separacion,
                                end = separacion,
                                top = separacion,
                                bottom = separacion
                            ),
                        text = "Imagen0"
                    )
                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .height(alto)
                            .padding(
                                start = separacion,
                                end = separacion,
                                top = separacion,
                                bottom = separacion
                            ),
                        text = "Imagen0"
                    )
                }

            }
            */
        }

    }


}

@Preview (showSystemUi = true)
@Composable
fun DinningCommandScreenPreview() {
    DinningCommandScreen()
}
