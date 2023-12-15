package com.integrame.app.teacher.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.integrame.app.core.data.fake.FakeResources
import com.integrame.app.core.ui.components.DynamicImage
import com.integrame.app.login.ui.components.IdentityCard
import com.integrame.app.tasks.data.model.GenericTask

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskTeacherScreen(
    onPressBack: () -> Unit,
    onPressNotifications: () -> Unit,
    onPressProfile: () -> Unit,
    modifier: Modifier = Modifier
) {

    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Tareas",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium)
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                navigationIcon = {
                    IconButton(onClick = onPressBack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Volver",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onPressNotifications) {
                        Icon(
                            imageVector = Icons.Filled.Notifications,
                            contentDescription = "Avisos y notificaciones",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    IconButton(onClick = onPressProfile) {

                        DynamicImage(
                            //image = teacherProfile.avatar,
                            image = FakeResources.remoteImages[0],
                            modifier = Modifier
                                .clip(CircleShape)
                                .semantics {
                                    contentDescription = "Perfil"
                                },
                            contentScale = ContentScale.Crop
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {



                LazyVerticalGrid(
                    columns = GridCells.Fixed(1),
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                    horizontalArrangement = Arrangement.spacedBy(24.dp),
                    contentPadding = PaddingValues(16.dp),
                    modifier = modifier,
                    userScrollEnabled = false
                ) {
                    var listaTareas = FakeResources.genericTasks;

                    items(listaTareas) { listaTareas ->
                        Button(
                            onClick = { /*TODO*/ },
                            modifier = Modifier.height(120.dp),
                            shape = RoundedCornerShape(16.dp),

                            contentPadding = PaddingValues(6.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                                contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                        ) {

                            Column (
                                modifier = Modifier
                                    .height(70.dp)
                                    .width(70.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ){
                                DynamicImage(
                                    //image = teacherProfile.avatar,
                                    image = FakeResources.remoteImages[0],
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .semantics {
                                            contentDescription = "Perfil"
                                        },
                                    contentScale = ContentScale.Crop
                                )
                            }
                            Column(
                                verticalArrangement = Arrangement.SpaceBetween,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = listaTareas.displayName,
                                    textAlign = TextAlign.Center,
                                    style = MaterialTheme.typography.titleMedium
                                        .copy(
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = 20.sp
                                        )
                                )

                                Text(
                                    text = "Numero de pasos " + listaTareas.steps.size,
                                    textAlign = TextAlign.Center,
                                    style = MaterialTheme.typography.titleMedium
                                        .copy(
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = 16.sp
                                        )
                                )

                                Text(
                                    text = "Alumno:  " + "Nombre del alumno",
                                    textAlign = TextAlign.Center,
                                    style = MaterialTheme.typography.titleMedium
                                        .copy(
                                            fontWeight = FontWeight.SemiBold,
                                            fontSize = 16.sp
                                        )
                                )
                            }
                        }
                    }
                }

            }


    }
}

@Preview(showBackground = true)
@Composable
fun TaskTeacherScreenPreview() {
    TaskTeacherScreen(
        onPressBack = {},
        onPressNotifications = {},
        onPressProfile = {}
    )
}
