package com.integrame.app.tasks.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.integrame.app.core.ui.components.appbar.PaginatedBottomAppBar
import com.integrame.app.ui.theme.IntegraMeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenericMaterialTaskScreen(
    modifier: Modifier = Modifier
) {
    LaunchedEffect(Unit) {

    }

    Scaffold(
        modifier = modifier,
        topBar = {
            Surface(
                modifier = Modifier.height(80.dp),
                color = MaterialTheme.colorScheme.primaryContainer
            ) {}
        },
        bottomBar = {
            PaginatedBottomAppBar(
                currentPage = 0,
                isFirstPage = false,
                isLastPage = false,
                onPressNext = { /*TODO*/ },
                onPressPrevious = { /*TODO*/ }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                // TODO: Imagen clase
                Box(modifier = Modifier
                    .size(92.dp)
                    .background(Color.Blue)
                    .align(Alignment.CenterVertically)
                ) {}

            }

            Text(text = "Aquí va el nombre del material",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium)
                )

            Column(modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                ,
                horizontalAlignment = Alignment.CenterHorizontally)
                {
                // TODO : Material
                Box(modifier = Modifier
                    .padding(16.dp)
                    .size(width = 120.dp, height = 120.dp)
                    .background(Color.Green)
                ) {}

                Row(
                    modifier = Modifier
                        .padding(16.dp)
                ){
                    Column(modifier = Modifier,
                        horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                        Text(text = "Color",
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium)
                        )
                        // TODO : Color
                        Box(modifier = Modifier
                            .padding(16.dp)
                            .size(width = 120.dp, height = 120.dp)
                            .background(Color.Blue)
                        ) {}

                    }

                    Column(modifier = Modifier,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "Cantidad",
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium)

                        )
                        // TODO : Cantidad
                        Box(modifier = Modifier
                            .padding(16.dp)
                            .size(width = 120.dp, height = 120.dp)
                            .background(Color.Red)
                        ) {}

                    }
                }
            }

            Row(modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.End){

                Box(modifier = Modifier
                    .padding(32.dp)
                    .size(32.dp)
                    .align(Alignment.CenterVertically)

                ) {
                    Checkbox(checked = true, onCheckedChange = {}, modifier = Modifier.scale(2f))
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun GenericMaterialTaskScreenPreview() {
    IntegraMeTheme {
        GenericMaterialTaskScreen(
                modifier = Modifier.fillMaxSize()
        )
    }
}
