package com.integrame.app.login.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.integrame.app.core.data.model.session.Session
import com.integrame.app.core.data.repository.SessionRepositoryImpl
import com.integrame.app.core.util.Option
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestLoginViewModel @Inject constructor(
    private val sessionRepository: SessionRepositoryImpl
) : ViewModel() {
    fun logout() {
        viewModelScope.launch {
            sessionRepository.logout()
        }
    }

    fun startSession(user: String, password: String)
    {
        viewModelScope.launch {
            sessionRepository.startSession(2, "Soy el token de $user")
        }
    }

    fun getSession(): Flow<Option<Session>> {
        return sessionRepository.sessionFlow
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestLogin(
    testLoginViewModel: TestLoginViewModel = hiltViewModel()
)
{
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    val sessionState by testLoginViewModel.getSession().collectAsStateWithLifecycle(initialValue = Option.None)

    when (sessionState) {
        is Option.None -> {
            Column {
                TextField(
                    value = username,
                    onValueChange = {
                        username = it
                    },
                )
                TextField(
                    value = password,
                    onValueChange = {
                        password = it
                    }
                )
                Button(onClick = {
                    testLoginViewModel.startSession(username, password)
                }) {
                    Text(text = "Login")
                }
            }
        }
        is Option.Some<Session> -> {
            val session = (sessionState as Option.Some<Session>).value

            Column() {
                Text(text = "Sesión iniciada correctamente")
                Text(text = session.token)
                Button(onClick = {
                    testLoginViewModel.logout()
                }) {
                    Text(text = "Logout")
                }
            }
        }
    }
}