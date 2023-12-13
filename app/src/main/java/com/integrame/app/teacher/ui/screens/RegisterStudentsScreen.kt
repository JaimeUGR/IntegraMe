package com.integrame.app.teacher.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.integrame.app.core.data.fake.FakeResources

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterStudentsScreen(
    //teacherProfile: TeacherProfile,
    onSignOut: () -> Unit,
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
                        text = "Registrar alumnos",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium)
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                navigationIcon = {
                    IconButton(onClick = onSignOut) {
                        Icon(
                            imageVector = Icons.Filled.Logout,
                            contentDescription = "Cerrar sesión",
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
                        /*
                        DynamicImage(
                            image = teacherProfile.avatar,
                            modifier = Modifier
                                .clip(CircleShape)
                                .semantics {
                                    contentDescription = "Perfil"
                                },
                            contentScale = ContentScale.Crop
                        )
                        */
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
                .padding(16.dp)
        ) {
            // TODO: Si únicamente estarán las acciones seleccionables, quitar la column
            LazyVerticalGrid(
                columns = GridCells.Fixed(1),
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                var listStudents = FakeResources.studentProfiles
                items(listStudents) { listStudents ->
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier.height(80.dp),
                        shape = RoundedCornerShape(16.dp),
                        contentPadding = PaddingValues(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer,
                            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    ) {

                        Column(
                            verticalArrangement = Arrangement.SpaceBetween,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Text(
                                text = listStudents.name,
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.titleMedium
                                    .copy(
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 20.sp
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
fun RegisterStudentsScreenPreview() {
    RegisterStudentsScreen(
        onSignOut = {},
        onPressNotifications = {},
        onPressProfile = {}
    )
}
