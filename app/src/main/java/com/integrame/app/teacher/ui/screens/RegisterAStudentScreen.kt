package com.integrame.app.teacher.ui.screens

import android.widget.EditText
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.integrame.app.core.data.fake.FakeResources

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterAStudentScreen(
    //teacherProfile: TeacherProfile,
    onSignOut: () -> Unit,
    onPressNotifications: () -> Unit,
    onPressProfile: () -> Unit,
    modifier: Modifier = Modifier
) {

    val checkedImage = remember { mutableStateOf(false) }
    val checkedText = remember { mutableStateOf(false) }
    val checkedAudio = remember { mutableStateOf(false) }
    val checkedVideo = remember { mutableStateOf(false) }

    val nickName = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Registrar a un alumnos",
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
    ){ innerPadding ->
        Column (
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ){
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ){
                Box(//Este box simula el objeto que se usara para definir el avatar
                    modifier = Modifier
                        .size(130.dp)
                        .background(Color.Blue)
                        .padding(16.dp)
                ) {
                    // Contenido de la celda (puedes poner cualquier cosa aquí)
                }
                Column (
                    modifier = Modifier.padding(16.dp)
                ){
                    Row {
                        Text(
                            text = "Nombre: ",//Añadir nombre del alumno seleccionado anteriormente
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                        )
                    }
                    Row {
                        Text(
                            text = "Apellidos: ",//Añadir apellidos del alumno seleccionado anteriormente
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                        )
                    }


                    Row {
                        OutlinedTextField(
                            value = nickName.value,
                            onValueChange = {
                                // Actualizar el estado del texto cuando cambia
                                nickName.value = it
                            },
                            label = { Text(text = "NickName")},
                            placeholder = { Text(text = "NickName")}
                        )
                    }
                }
            }
            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = "Tipo de perfil:",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                )
            }


            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Imagen",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                )
                Checkbox(
                    checked = checkedImage.value,
                    onCheckedChange = {checkedImage.value = it},
                )

                Text(
                    text = "Texto",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                )
                Checkbox(
                    checked = checkedText.value,
                    onCheckedChange = {checkedText.value = it},
                )

            }
            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Audio",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                )
                Checkbox(
                    checked = checkedAudio.value,
                    onCheckedChange = {checkedAudio.value = it},
                )

                Text(
                    text = "Video",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                )
                Checkbox(
                    checked = checkedVideo.value,
                    onCheckedChange = {checkedVideo.value = it},
                )
            }

            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Establecer contraseña",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                )
            }

            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Texto: ",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                )
                OutlinedTextField(
                    modifier = Modifier.width(180.dp),
                    value = password.value,
                    onValueChange = {
                        // Actualizar el estado del texto cuando cambia
                        password.value = it
                    },
                    label = { Text(text = "Contraseña")},
                    placeholder = { Text(text = "Contraseña")}
                )
                Button(onClick = { /*TODO*/ }) {
                    Text(
                        text = "Guardar",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                    )
                }
            }
            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = "Elegir set de imagenes:",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun RegisterAStudentScreenPreview() {
    RegisterAStudentScreen(
        onSignOut = {},
        onPressNotifications = {},
        onPressProfile = {}
    )
}