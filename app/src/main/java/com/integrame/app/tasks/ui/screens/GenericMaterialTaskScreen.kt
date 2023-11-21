package com.integrame.app.tasks.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.integrame.app.core.data.fake.FakeResources
import com.integrame.app.core.ui.components.DynamicImage
import com.integrame.app.core.ui.components.PaginatedBottomAppBar
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
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(60.dp)
            ) {

                // TODO: Imagen clase
                Box(modifier = Modifier
                    .size(92.dp)
                    .background(Color.Blue)
                    .align(Alignment.CenterVertically)
                ) {}

                // TODO: Row con Entregado y tick
                Row(modifier = Modifier
                    .background(color = Color.Cyan)
                    ,
                    horizontalArrangement = Arrangement.End){

                    // TODO: Foto entregado
                    Box(modifier = Modifier
                        .padding(16.dp)
                        .size(64.dp)
                        .background(Color.Red)
                        .align(Alignment.CenterVertically)
                        .padding()
                    ) {}

                    // TODO : TICK
                    Box(modifier = Modifier
                        .padding(16.dp)
                        .size(32.dp)
                        //.background(Color.Green)
                        .align(Alignment.CenterVertically)

                    ) {
                        Checkbox(checked = true, onCheckedChange = {}, modifier = Modifier.size(120.dp))
                    }
                }
            }

            Text(text = "Aquí va el nombre del material",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium)

                )

            // imagen material, color y cantidad
            Column(modifier = Modifier
                .background(Color.LightGray)
                .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally)
                {
                // TODO : Material
                Box(modifier = Modifier
                    .padding(16.dp)
                    .size(width = 120.dp, height = 120.dp)
                    .background(Color.Green)
                ) {}
                // Color y Cantidad
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
