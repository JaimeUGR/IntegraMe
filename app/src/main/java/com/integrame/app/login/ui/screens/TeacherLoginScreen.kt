package com.integrame.app.login.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.integrame.app.core.data.model.content.VectorImage
import com.integrame.app.core.ui.components.ErrorCard
import com.integrame.app.login.ui.viewmodel.AuthProcessUIState
import com.integrame.app.login.ui.viewmodel.TeacherLoginViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeacherLoginScreen(
    onNavigateBack: () -> Unit,
    onAuthorized: () -> Unit,
    modifier: Modifier = Modifier,
    teacherLoginViewModel: TeacherLoginViewModel = hiltViewModel()
) {
    val nickname = teacherLoginViewModel.nickname
    val password = teacherLoginViewModel.password
    var passwordVisible by remember { mutableStateOf(false) }

    val authProcessUIState = teacherLoginViewModel.authProcessUIState

    // Proceso de autenticación
    if (authProcessUIState !is AuthProcessUIState.Pending) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .zIndex(2f)
                .background(Color(0.2f, 0.2f, 0.2f, 0.1f))
                .clickable(enabled = false) { }
        ) {
            when (authProcessUIState) {
                is AuthProcessUIState.Requesting -> CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(80.dp)
                )
                is AuthProcessUIState.Authorized -> LaunchedEffect(Unit) {
                    onAuthorized()
                }
                else -> {
                    val message =
                        if (authProcessUIState is AuthProcessUIState.Error) authProcessUIState.error
                        else "Usuario y/o contraseña incorrectos" // Error de autenticación

                    ErrorCard(
                        errorDescription = message,
                        errorButtonText = "Cerrar",
                        errorButtonImage = {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = "Cerrar",
                                tint = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        },
                        onPressContinue = { teacherLoginViewModel.resetAuthProcess() },
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Identificación Docente",
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
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().semantics { testTag = "textUser" },
                value = nickname,
                onValueChange = { teacherLoginViewModel.onNicknameChange(it) },
                label = { Text(text = "Usuario")},
                placeholder = { Text(text = "Usuario")},
                singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().semantics { testTag = "textPassword" },
                value = password,
                onValueChange = { teacherLoginViewModel.onPasswordChange(it) },
                label = { Text(text = "Contraseña")},
                placeholder = { Text(text = "Contraseña")},
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val icon = if (passwordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility
                    val description = if (passwordVisible) "Mostrar contraseña" else "Ocultar contraseña"

                    IconButton(onClick = {passwordVisible = !passwordVisible}){
                        Icon(
                            imageVector = icon,
                            contentDescription = description,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { teacherLoginViewModel.onSignIn() },
                modifier = Modifier.fillMaxWidth(0.8f).semantics { testTag = "signInButton" },
                enabled = nickname.isNotEmpty() &&
                        password.isNotEmpty() &&
                        teacherLoginViewModel.authProcessUIState == AuthProcessUIState.Pending
            ) {
                Text(
                    text = "Iniciar Sesión",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium)
                )
            }
        }
    }
}
