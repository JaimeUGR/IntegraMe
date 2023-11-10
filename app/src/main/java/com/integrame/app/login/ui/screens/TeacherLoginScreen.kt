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
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.integrame.app.core.ui.components.ErrorCard
import com.integrame.app.login.ui.viewmodel.AuthProcessUIState
import com.integrame.app.login.ui.viewmodel.TeacherLoginViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeacherLoginScreen(
    onAuthorized: () -> Unit,
    onErrorScreenButtonClick: () -> Unit,
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
                // Tarjeta de error
            }
            else if (authProcessUIState is AuthProcessUIState.Error) {
                ErrorCard(
                    errorDescription = authProcessUIState.error,
                    onPressContinue = { },//studentAuthViewModel.resetAuthProcess() },
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Identificación Docente",
            modifier = Modifier.padding(vertical = 8.dp),
            style = MaterialTheme.typography.titleLarge
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = nickname,
            onValueChange = { teacherLoginViewModel.onNicknameChange(it) },
            label = { Text(text = "Usuario")},
            placeholder = { Text(text = "Usuario")},
            singleLine = true
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
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
                    Icon(imageVector = icon, contentDescription = description)
                }
            }
        )

        Spacer(modifier = Modifier.height(15.dp))

        Button(
            onClick = { teacherLoginViewModel.onSignIn() },
            modifier = Modifier.fillMaxWidth(0.6f),
            enabled = nickname.isNotEmpty() &&
                    password.isNotEmpty() &&
                    teacherLoginViewModel.authProcessUIState == AuthProcessUIState.Pending
        ) {
            Text(text = "Iniciar Sesión")
        }
    }
}
