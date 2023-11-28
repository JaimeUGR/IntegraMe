package com.integrame.app.tasks.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
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
import coil.compose.AsyncImage
import com.integrame.app.core.data.fake.FakeResources
import com.integrame.app.core.data.fake.NUM_TASKS
import com.integrame.app.core.data.model.content.AudioContent
import com.integrame.app.core.data.model.content.ContentAdaptationFormats
import com.integrame.app.core.data.model.content.ContentPack
import com.integrame.app.core.data.model.content.ContentProfile
import com.integrame.app.core.data.model.content.TextContent
import com.integrame.app.core.data.model.content.VideoContent
import com.integrame.app.core.data.network.toContentProfile
import com.integrame.app.core.ui.components.DynamicImage
import com.integrame.app.core.ui.components.appbar.PaginatedBottomAppBar
import com.integrame.app.core.ui.components.appbar.StudentTaskTopAppBar
import com.integrame.app.tasks.data.model.GenericTask
import com.integrame.app.tasks.data.model.GenericTaskStep
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
    task: GenericTask,
    contentProfile: ContentProfile,
    onNavigateBack: () -> Unit,
    onPressHome: () -> Unit,
    onPressChat: () -> Unit,
    modifier: Modifier = Modifier
) {
    // TODO: Pendiente de integrar en el viewmodel
    val currentStep: GenericTaskStep = FakeResources.genericTasks[0].steps[0]
    var stepCompleted by rememberSaveable { mutableStateOf(false) }

    val contentAdaptationFormats = remember { contentProfile.getContentAdaptationFormatsAsList() }
    var selectedAdaptationFormat by rememberSaveable { mutableIntStateOf(0) }

    Scaffold(
        modifier = modifier,
        topBar = {
            StudentTaskTopAppBar(
                title = task.displayName,
                onNavigateBack = onNavigateBack,
                onPressHome = onPressHome,
                onPressChat = onPressChat
            )
        },
        bottomBar = {
            PaginatedBottomAppBar(
                currentPage = 0,
                isFirstPage = true, // TODO: Paso actual == inicial
                isLastPage = !stepCompleted,
                onPressNext = { /*TODO*/ },
                onPressPrevious = { /*TODO*/ },
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .fillMaxSize()
        ) {

            // Imagen de la tarea
            DynamicImage(
                image = task.displayImage,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .height(160.dp)
                    .align(Alignment.CenterHorizontally)
            )
            
            Spacer(modifier = Modifier.height(24.dp))

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
                                    selectedAdaptationFormat = i
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
                                text = currentStep.content.text.text
                            )
                        }
                        ContentAdaptationFormats.Image -> {
                            DynamicImage(
                                image = currentStep.content.image,
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
                onClick = { stepCompleted = !stepCompleted },
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(16.dp).height(64.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Completado",
                    modifier = Modifier.padding(end = 32.dp),
                    style = MaterialTheme.typography.titleLarge
                )
                Checkbox(
                    checked = stepCompleted,
                    onCheckedChange = null, // No es clickable
                    modifier = Modifier.scale(1.5f)
                )
            }
        }
    }
}

@Preview(showSystemUi = false)
@Composable
fun GenericTaskScreenPreview() {
    IntegraMeTheme {
        GenericTaskScreen(
            task = FakeResources.genericTasks[0],
            contentProfile = FakeResources.contentProfiles[0].toContentProfile(),
            onNavigateBack = { /*TODO*/ },
            onPressHome = { /*TODO*/ },
            onPressChat = { /*TODO*/ },
            modifier = Modifier.fillMaxSize()
        )
    }
}
