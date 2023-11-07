package com.integrame.app.login.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp;
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.integrame.app.R
import com.integrame.app.core.data.model.content.ContentProfile
import com.integrame.app.core.data.model.content.ImageContent
import com.integrame.app.login.data.model.ImageAuthMethod
import com.integrame.app.login.data.model.TextAuthMethod
import com.integrame.app.login.ui.components.IdentityCard
import com.integrame.app.login.ui.viewmodel.StudentAuthUIState
import com.integrame.app.login.ui.viewmodel.StudentAuthViewModel

@Composable
fun StudentAuthScreen(
    userId: Int,
    onErrorScreenButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    studentAuthViewModel: StudentAuthViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        if (studentAuthViewModel.studentAuthUIState == StudentAuthUIState.Loading)
        {
            Log.i("studentAuthScreen", "LaunchedEffect: Cargar perfil de autenticación del estudiante")
            studentAuthViewModel.loadStudentData(userId)
        }
    }

    when (val authUIState = studentAuthViewModel.studentAuthUIState) {
        is StudentAuthUIState.UserLoaded -> {
            val authProfile = authUIState.studentAuthProfile

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                IdentityCard(
                    identityCard = authProfile.identityCard,
                    onCardClick = {},
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Divider(
                    modifier = Modifier.padding(vertical = 16.dp),
                    color = Color.Gray,
                    thickness = 2.dp
                )

                when (authProfile.authMethod) {
                    is TextAuthMethod -> {
                        TextAuth(
                            authProfile.contentProfile
                        )
                    }
                    is ImageAuthMethod -> {
                        ImageAuth(
                            authProfile.authMethod.steps,
                            authProfile.authMethod.imageList,
                            authProfile.contentProfile
                        )
                    }
                }
            }
        }
        is StudentAuthUIState.Loading -> {
            Box(modifier = modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier
                    .align(Alignment.Center)
                    .size(80.dp))
            }
        }
        is StudentAuthUIState.Error -> {
            Column(
                modifier = modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    // TODO: Poner una imagen de X roja
                    painter = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = "X de color rojo que indica un error"
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = authUIState.error
                )
                Spacer(modifier = Modifier.height(30.dp))
                Button(onClick = onErrorScreenButtonClick) {
                    Image(
                        // TODO: Poner una imagen de puerta para salir
                        painter = painterResource(R.drawable.ic_launcher_foreground),
                        contentDescription = "Volver atrás"
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TextAuth(
    contentProfile: ContentProfile,
    modifier: Modifier = Modifier
) {
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = modifier,
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = { password = it},
            label = { Text(text = "Contraseña")},
            placeholder = { Text(text = "Contraseña")},
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {

                val icon = if (passwordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility
                val description = if (passwordVisible) "Mostrar contraseña" else "Ocultar contraseña"

                IconButton(onClick = {passwordVisible = !passwordVisible}){
                    Icon(imageVector = icon, contentDescription = description)
                }
            }
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            enabled = password.isNotEmpty(),
            onClick = { /*TODO*/ }
        ) {
            Text(text = "Iniciar sesión")
        }
    }
}

@Composable
private fun ImageAuth(
    steps: Int,
    images: List<ImageContent>,
    contentProfile: ContentProfile,
    modifier: Modifier = Modifier
) {
    var imagePassword by rememberSaveable { mutableStateOf(emptyList<Int>()) }

    Column(
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(30.dp, alignment = Alignment.CenterHorizontally)
        ) {

            repeat(steps) { i ->
                Box(modifier = Modifier
                    .size(32.dp)
                    .background(
                        color =
                        if (i == imagePassword.size) Color.Yellow
                        else if (i > imagePassword.size) Color.Gray
                        else Color.Green, shape = CircleShape
                    )
                )
            }
        }
        LazyVerticalGrid(
            modifier = Modifier.padding(vertical = 24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            columns = GridCells.Fixed(2)
        ) {
            items(images) { imageContent ->
                AsyncImage(
                    modifier = Modifier
                        .border(4.dp, Color.Black)
                        .clickable(
                            onClickLabel = "Añadir imagen a la contraseña",
                            enabled = imagePassword.size < steps
                        ) {
                            imagePassword = imagePassword
                                .toMutableList()
                                .apply { add(imageContent.id) }

                            if (imagePassword.size >= steps)
                            // TODO
                                Log.i("imagePassword", "Auth Ready")
                        },
                    model = imageContent.imageUrl,
                    contentDescription = imageContent.altDescription
                )
            }
        }
        IconButton(
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            onClick = { imagePassword = imagePassword.dropLast(1) },
            enabled = imagePassword.isNotEmpty()
        ) {
            Icon(
                modifier = Modifier.size(32.dp),
                imageVector = Icons.Filled.Backspace,
                contentDescription = "Borrar una imagen",
                tint = if (imagePassword.isNotEmpty()) Color.Red else Color.Gray
            )
        }
    }
}

