package com.integrame.app.login.ui.screens

import android.util.Log
import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Replay
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp;
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.integrame.app.R
import com.integrame.app.core.data.model.content.ContentProfile
import com.integrame.app.core.data.model.content.ImageContent
import com.integrame.app.core.data.model.content.VectorImage
import com.integrame.app.core.ui.components.DynamicImage
import com.integrame.app.core.ui.components.ErrorCard
import com.integrame.app.login.data.model.ImageAuthMethod
import com.integrame.app.login.data.model.TextAuthMethod
import com.integrame.app.login.ui.components.IdentityCard
import com.integrame.app.login.ui.viewmodel.AuthProcessUIState
import com.integrame.app.login.ui.viewmodel.StudentAuthUIState
import com.integrame.app.login.ui.viewmodel.StudentAuthViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentAuthScreen(
    userId: Int,
    onNavigateBack: () -> Unit,
    onAuthorized: () -> Unit,
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
            val authProcessUIState = studentAuthViewModel.authProcessUIState

            // Proceso de autenticación
            if (authProcessUIState !is AuthProcessUIState.Pending) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .zIndex(2f)
                        .background(Color(0.2f, 0.2f, 0.2f, 0.1f))
                        .clickable(enabled = false) { }
                ) {
                    if (authProcessUIState is AuthProcessUIState.Requesting)
                        CircularProgressIndicator(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .size(80.dp)
                        )
                    else if (authProcessUIState is AuthProcessUIState.Authorized)
                        LaunchedEffect(Unit) {
                            onAuthorized()
                        }
                    else if (authProcessUIState is AuthProcessUIState.Unauthorized) {
                        LaunchedEffect(Unit) {
                            delay(5000)
                            studentAuthViewModel.resetAuthProcess()
                        }

                        Canvas(
                            modifier = Modifier
                                .fillMaxSize()
                                .zIndex(1f)
                                .semantics {
                                    contentDescription = "Contraseña incorrecta"
                                }
                        ) {
                            val startX = 0f
                            val startY = 0f
                            val endX = size.width
                            val endY = size.height
                            drawLine(Color.Red, Offset(startX, startY), Offset(endX, endY), strokeWidth = 32f)
                            drawLine(Color.Red, Offset(endX, 0f), Offset(0f, endY), strokeWidth = 32f)
                        }
                    }
                    else if (authProcessUIState is AuthProcessUIState.Error) {
                        ErrorCard(
                            errorDescription = authProcessUIState.error,
                            errorButtonText = "Cerrar",
                            errorButtonImage = {
                                Icon(
                                    imageVector = Icons.Filled.Close,
                                    contentDescription = "Cerrar",
                                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            },
                            onPressContinue = { studentAuthViewModel.resetAuthProcess() },
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }

            // Pantalla principal
            val authProfile = authUIState.studentAuthProfile

            Scaffold(
                modifier = modifier,
                topBar = {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(
                                text = authProfile.identityCard.nickname,
                                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Medium)
                            )
                        },
                        navigationIcon = {
                            IconButton(onClick = onNavigateBack) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = "Retroceder",
                                    modifier = Modifier.size(32.dp),
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        },
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                            titleContentColor = MaterialTheme.colorScheme.primary,
                        )
                    )
                }
            ) { innerPadding ->
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
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
                                contentProfile = authProfile.contentProfile,
                                onTextPasswordChange = { studentAuthViewModel.onTextPasswordChange(it) },
                                onSignIn = { studentAuthViewModel.textAuthSignIn(userId) },
                                studentAuthViewModel = studentAuthViewModel
                            )
                        }
                        is ImageAuthMethod -> {
                            ImageAuth(
                                steps = authProfile.authMethod.steps,
                                images = authProfile.authMethod.imageList,
                                contentProfile = authProfile.contentProfile,
                                onSelectImage = { idx -> studentAuthViewModel.onAddImage(authProfile.authMethod.imageList[idx].id, idx) },
                                onRemoveImage = { studentAuthViewModel.onRemoveImage() },
                                onSignIn = { studentAuthViewModel.imageAuthSignIn(userId) },
                                studentAuthViewModel = studentAuthViewModel
                            )
                        }
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
            Box(modifier = Modifier.fillMaxSize()) {
                ErrorCard(
                    errorDescription = authUIState.error,
                    errorButtonText = "Cerrar",
                    errorButtonImage = {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "Cerrar",
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    },
                    onPressContinue = { studentAuthViewModel.resetAuthProcess() },
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TextAuth(
    contentProfile: ContentProfile,
    onTextPasswordChange: (String) -> Unit,
    onSignIn: () -> Unit,
    modifier: Modifier = Modifier,
    studentAuthViewModel: StudentAuthViewModel = hiltViewModel()
) {
    val textPassword = studentAuthViewModel.textPassword
    var passwordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().semantics { testTag = "textPassword" },
            value = textPassword,
            onValueChange = { onTextPasswordChange(it) },
            label = { Text(text = "Contraseña")},
            placeholder = { Text(text = "Contraseña")},
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Key, contentDescription = "Icono llave", tint = MaterialTheme.colorScheme.primary)
            },
            trailingIcon = {
                val icon = if (passwordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility
                val description = if (passwordVisible) "Mostrar contraseña" else "Ocultar contraseña"

                IconButton(onClick = {passwordVisible = !passwordVisible}){
                    Icon(imageVector = icon, contentDescription = description, tint = MaterialTheme.colorScheme.primary)
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier.fillMaxWidth(0.8f).semantics { testTag = "signInButton" },
            enabled = textPassword.isNotEmpty(),
            onClick = onSignIn
        ) {
            Text(
                text = "Iniciar Sesión",
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium)
            )
        }
    }
}

@Composable
private fun ImageAuth(
    steps: Int,
    images: List<ImageContent>,
    contentProfile: ContentProfile,
    onSelectImage: (Int) -> Unit,
    onRemoveImage: () -> Unit,
    onSignIn: () -> Unit,
    modifier: Modifier = Modifier,
    studentAuthViewModel: StudentAuthViewModel = hiltViewModel()
) {
    val imagePassword = studentAuthViewModel.imagePassword
    val passwordLength = imagePassword.size

    LaunchedEffect(imagePassword) {
        if (passwordLength >= steps)
            onSignIn()
    }

    Column(
        modifier = modifier,
    ) {
        LazyVerticalGrid(
            modifier = Modifier.padding(bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            columns = GridCells.Fixed(2)
        ) {
            itemsIndexed(images) { i, imageContent ->
                // TODO: Añadir semantics
                DynamicImage(
                    image = imageContent,
                    modifier = Modifier
                        .height(100.dp)
                        .clickable(
                            onClickLabel = "Añadir imagen a contraseña",
                            role = Role.Image,
                            enabled = passwordLength < steps
                        ) {
                            onSelectImage(i)
                        }
                        .semantics { testTag = "imagen contrasenia" },
                    contentScale = ContentScale.Crop
                )
            }
        }
        // TODO: eliminar este botón cuando esté el check por imagen introducida
        IconButton(
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            onClick = onRemoveImage,
            enabled = imagePassword.isNotEmpty()
        ) {
            Icon(
                modifier = Modifier.size(32.dp),
                imageVector = Icons.Filled.Backspace,
                contentDescription = "Borrar una imagen",
                tint = if (imagePassword.isNotEmpty()) Color.Red else Color.Gray
            )
        }
        Row(
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth()
                .semantics {
                    contentDescription =
                        "Te quedan por seleccionar ${steps - passwordLength} imágenes"
                },
            horizontalArrangement = Arrangement.spacedBy(30.dp, alignment = Alignment.CenterHorizontally)
        ) {
            repeat(steps) { i ->
                Box(
                    modifier = Modifier
                        .border(2.dp, Color.Gray)
                        .size(64.dp)
                        .background(
                            color = Color.LightGray
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    if (passwordLength > i)
                        DynamicImage(
                            image = images[imagePassword[i].second],
                            contentScale = ContentScale.Crop
                        )
                }
            }
        }
    }
}
