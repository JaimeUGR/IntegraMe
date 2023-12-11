package com.integrame.app.tasks.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.integrame.app.core.data.fake.FakeResources
import com.integrame.app.core.data.model.content.ContentProfile
import com.integrame.app.core.data.network.toContentProfile
import com.integrame.app.core.ui.components.DynamicImage
import com.integrame.app.core.ui.components.appbar.PaginatedBottomAppBar
import com.integrame.app.core.ui.components.appbar.StudentTaskTopAppBar
import com.integrame.app.tasks.data.model.MaterialTaskModel
import com.integrame.app.tasks.ui.viewmodel.MaterialTaskScreenViewModel
import com.integrame.app.tasks.ui.viewmodel.MaterialTaskUIState
import com.integrame.app.ui.theme.IntegraMeTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MaterialTaskScreen(
    taskModel: MaterialTaskModel,
    contentProfile: ContentProfile,
    onNavigateBack: () -> Unit,
    onPressHome: () -> Unit,
    onPressChat: () -> Unit,
    modifier: Modifier = Modifier,
    materialTaskScreenViewModel: MaterialTaskScreenViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        materialTaskScreenViewModel.loadTaskModel(taskModel)
    }

    val materialTaskUIState = materialTaskScreenViewModel.materialTaskUIState

    Scaffold(
        modifier = modifier,
        topBar = {
            StudentTaskTopAppBar(
                title = taskModel.displayName,
                onNavigateBack = onNavigateBack,
                onPressHome = onPressHome,
                onPressChat = onPressChat
            )
        },
        bottomBar = {
            var isFirstPage = materialTaskUIState !is MaterialTaskUIState.InReward
            var isLastPage = true

            if (materialTaskUIState is MaterialTaskUIState.InRequest) {
                isFirstPage = materialTaskUIState.requestNumber <= 0
                isLastPage = !materialTaskUIState.isDelivered
            }

            PaginatedBottomAppBar(
                currentPage = 0,
                isFirstPage = isFirstPage,
                isLastPage = isLastPage,
                onPressNext = { materialTaskScreenViewModel.nextRequest() },
                onPressPrevious = { materialTaskScreenViewModel.previousRequest() },
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        }
    ) { innerPadding ->
        val baseModifier = Modifier
            .padding(innerPadding)
            .padding(16.dp)
            .fillMaxSize()

        when (materialTaskUIState) {
            is MaterialTaskUIState.Loading -> {
                Box(
                    modifier = Modifier.padding(innerPadding).fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(modifier = Modifier.size(80.dp))
                }
            }
            is MaterialTaskUIState.InRequest -> {
                MaterialTaskRequest(
                    requestState = materialTaskUIState,
                    onToggleDelivered = { materialTaskScreenViewModel.toggleRequestDelivered() },
                    modifier = baseModifier
                )
            }
            is MaterialTaskUIState.InReward -> {
                Box(modifier = baseModifier) {
                    DynamicImage(
                        image = taskModel.displayImage,
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .height(160.dp)
                            .align(Alignment.TopCenter)
                    )

                    // TODO: DynamicContent con la reward
                    AsyncImage(
                        model = "https://static.arasaac.org/pictograms/5397/5397_300.png",
                        contentDescription = "Has terminado, ¡bien!",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Composable
private fun MaterialTaskRequest(
    requestState: MaterialTaskUIState.InRequest,
    onToggleDelivered: () -> Unit,
    modifier: Modifier = Modifier
) {
    val material = requestState.material

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .padding(16.dp).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = material.displayName.text,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium)
            )

            DynamicImage(
                image = material.displayImage,
                modifier = Modifier.size(120.dp)
            )

            Row(
                modifier = Modifier.padding(16.dp)
            ) {
                if (material.property != null) {
                    val property = material.property

                    Column(modifier = Modifier,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = property.displayName.text,
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium)
                        )

                        DynamicImage(
                            image = property.displayImage,
                            modifier = Modifier
                                .padding(16.dp)
                                .size(width = 120.dp, height = 120.dp)
                        )
                    }
                }


                Column(modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Cantidad",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium)
                    )

                    DynamicImage(
                        image = requestState.displayAmount,
                        modifier = Modifier
                            .padding(16.dp)
                            .size(width = 120.dp, height = 120.dp)
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AsyncImage(
                model = "https://static.arasaac.org/pictograms/9815/9815_300.png",
                contentDescription = "Imagen de la clase X",
                modifier = Modifier.height(120.dp)
            )

            Button(
                onClick = onToggleDelivered,
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(20.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Entregado",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.paddingFromBaseline(bottom = 20.dp)
                    )

                    AsyncImage(
                        model = "https://static.arasaac.org/pictograms/17038/17038_300.png",
                        contentDescription = "Imagen de niño entregando un objeto",
                        modifier = Modifier
                            .height(100.dp)
                            .background(color = Color.White)
                            .border(2.dp, Color.Black),
                    )
                }

                Checkbox(
                    checked = requestState.isDelivered,
                    onCheckedChange = null, // No es clickable
                    modifier = Modifier
                        .padding(start = 24.dp)
                        .scale(1.5f)
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MaterialTaskScreenPreview() {
    IntegraMeTheme {
        MaterialTaskScreen(
            taskModel = MaterialTaskModel.fromMaterialTask(FakeResources.materialTasks[0]),
            contentProfile = FakeResources.contentProfiles[0].toContentProfile(),
            onNavigateBack = {},
            onPressHome = {},
            onPressChat = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}

