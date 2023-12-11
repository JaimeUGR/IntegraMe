package com.integrame.app.tasks.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.integrame.app.core.data.fake.FakeResources
import com.integrame.app.core.data.model.content.ContentAdaptationFormats
import com.integrame.app.core.data.model.content.ContentProfile
import com.integrame.app.core.data.network.toContentProfile
import com.integrame.app.core.ui.components.DynamicImage
import com.integrame.app.core.ui.components.appbar.PaginatedBottomAppBar
import com.integrame.app.core.ui.components.appbar.StudentTaskTopAppBar
import com.integrame.app.tasks.data.model.GenericTaskModel
import com.integrame.app.tasks.ui.viewmodel.GenericTaskScreenViewModel
import com.integrame.app.tasks.ui.viewmodel.GenericTaskUIState
import com.integrame.app.tasks.ui.viewmodel.MaterialTaskUIState
import com.integrame.app.ui.theme.IntegraMeTheme

val contentSelectorCards = mapOf<ContentAdaptationFormats, @Composable (Modifier) -> Unit>(
    ContentAdaptationFormats.Text to { modifier ->
        Text(
            text = "Texto",
            modifier = modifier,
            textAlign = TextAlign.Center
        )
    },
    ContentAdaptationFormats.Image to { modifier ->
        AsyncImage(
            model = "https://static.arasaac.org/pictograms/7107/7107_300.png",
            contentDescription = "Seleccionar imagen",
            modifier = modifier
        )
    },
    ContentAdaptationFormats.Audio to { modifier ->
        AsyncImage(
            model = "https://static.arasaac.org/pictograms/29720/29720_300.png",
            contentDescription = "Seleccionar audio",
            modifier = modifier
        )
    },
    ContentAdaptationFormats.Video to { modifier ->
        AsyncImage(
            model = "https://static.arasaac.org/pictograms/6626/6626_300.png",
            contentDescription = "Seleccionar vídeo",
            modifier = modifier
        )
    }
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenericTaskScreen(
    taskModel: GenericTaskModel,
    contentProfile: ContentProfile,
    onNavigateBack: () -> Unit,
    onPressHome: () -> Unit,
    onPressChat: () -> Unit,
    modifier: Modifier = Modifier,
    genericTaskScreenViewModel: GenericTaskScreenViewModel = hiltViewModel()
) {
    val genericTaskUIState = genericTaskScreenViewModel.genericTaskUIState
    val contentAdaptationFormats = remember { contentProfile.getContentAdaptationFormatsAsList() }
    var selectedAdaptationFormat by rememberSaveable { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        genericTaskScreenViewModel.loadTaskModel(taskModel)
    }

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
            var isFirstPage = genericTaskUIState !is GenericTaskUIState.InReward
            var isLastPage = true

            if (genericTaskUIState is GenericTaskUIState.InStep) {
                isFirstPage = genericTaskUIState.stepNumber == 0
                isLastPage = !genericTaskUIState.isCompleted
            }

            PaginatedBottomAppBar(
                currentPage = 0,
                isFirstPage = isFirstPage,
                isLastPage = isLastPage,
                onPressNext = { genericTaskScreenViewModel.nextStep() },
                onPressPrevious = { genericTaskScreenViewModel.previousStep() },
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        }
    ) { innerPadding ->
        if (genericTaskUIState is GenericTaskUIState.Loading) {
            Box(
                modifier = Modifier.padding(innerPadding).fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(modifier = Modifier.size(80.dp))
            }

            return@Scaffold
        }

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .fillMaxSize()
        ) {
            // Imagen de la tarea
            DynamicImage(
                image = taskModel.displayImage,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .height(160.dp)
                    .align(Alignment.CenterHorizontally)
            )
            
            Spacer(modifier = Modifier.height(24.dp))

            if (genericTaskUIState is GenericTaskUIState.InStep) {
                GenericTaskStepContent(
                    stepState = genericTaskUIState,
                    contentAdaptationFormats = contentAdaptationFormats,
                    selectedAdaptationFormat = selectedAdaptationFormat,
                    onSelectAdaptationFormat = { i -> selectedAdaptationFormat = i},
                    onToggleStepCompleted = { genericTaskScreenViewModel.toggleStepCompleted() }
                )
            } else { // Recompensa
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    // TODO: Añadir el DynamicContent con la reward
                    AsyncImage(model = "https://static.arasaac.org/pictograms/5397/5397_300.png", contentDescription = "Has terminado, ¡bien!")
                }
            }
        }
    }
}

@Composable
fun GenericTaskStepContent(
    stepState: GenericTaskUIState.InStep,
    contentAdaptationFormats: List<ContentAdaptationFormats>,
    selectedAdaptationFormat: Int,
    onSelectAdaptationFormat: (Int) -> Unit,
    onToggleStepCompleted: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Caja de contenido
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Selectores de contenido
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                val selectableModifier = Modifier
                    .height(64.dp)

                repeat(contentAdaptationFormats.size) { i ->
                    Box(
                        modifier = selectableModifier
                            .background(
                                color = if (i == selectedAdaptationFormat) MaterialTheme.colorScheme.secondaryContainer
                                else MaterialTheme.colorScheme.surfaceVariant
                            )
                            .weight(1f)
                            .border(1.dp, Color.Black)
                            .clickable {
                                onSelectAdaptationFormat(i)
                            }
                            .padding(4.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        contentSelectorCards[contentAdaptationFormats[i]]?.invoke(Modifier)
                    }
                }
            }

            // Caja de contenido seleccionado
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(256.dp),
                contentAlignment = Alignment.Center
            ) {
                // Contenido seleccionado a mostrar
                when(contentAdaptationFormats[selectedAdaptationFormat]) {
                    ContentAdaptationFormats.Text -> {
                        Text(
                            text = stepState.stepContent.text.text
                        )
                    }
                    ContentAdaptationFormats.Image -> {
                        DynamicImage(
                            image = stepState.stepContent.image,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    ContentAdaptationFormats.Audio -> {
                        Text(
                            text = "Soy un audio",
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    ContentAdaptationFormats.Video -> {
                        Text(
                            text = "Soy un vídeo",
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { onToggleStepCompleted() },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .height(64.dp),
            shape = RoundedCornerShape(12.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            Text(
                text = "Completado",
                modifier = Modifier.padding(end = 32.dp),
                style = MaterialTheme.typography.titleLarge
            )
            Checkbox(
                checked = stepState.isCompleted,
                onCheckedChange = null, // No es clickable
                modifier = Modifier.scale(1.5f)
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun GenericTaskScreenPreview() {
    IntegraMeTheme {
        GenericTaskScreen(
            taskModel = GenericTaskModel.fromGenericTask(FakeResources.genericTasks[0]),
            contentProfile = FakeResources.contentProfiles[0].toContentProfile(),
            onNavigateBack = { /*TODO*/ },
            onPressHome = { /*TODO*/ },
            onPressChat = { /*TODO*/ },
            modifier = Modifier.fillMaxSize()
        )
    }
}
