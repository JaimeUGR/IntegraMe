package com.integrame.app.login.data.repository

import com.integrame.app.core.data.repository.SessionRepositoryImpl
import com.integrame.app.login.data.model.StudentPassword
import com.integrame.app.login.domain.repository.AuthRepository
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class AuthRepositoryImpl(
    private val sessionRepository: SessionRepositoryImpl
) : AuthRepository {

    // TODO: Si hay success en cualquier de estos dos métodos, hará una llamada al sessionRepository
    // para guardar el token de autenticación

    override suspend fun signInTeacher(user: String, password: String) : LoginState {
        return LoginState.Sucess
    }

    // TODO: Para el encode, lanzar hebra cpu
    override suspend fun signInStudent(user: Int, password: StudentPassword) : LoginState {
        // construir la contraseña a partir del contenido dinámico que ha seleccionado el usuario
        val jsonPassword = Json.encodeToString(password)

        /*
            Idea de construcción: JSON
            [{ valor: X, tipoContenido: texto }, {...}].

            Luego en el backend, se lee el tipo de contenido y si es una imagen el valor se interpreta como un int
            y se entiende que es la id interna
         */
        return LoginState.Failed("Error autenticación")
    }
}

sealed interface LoginState {
    object Sucess : LoginState
    data class Failed(val error: String) : LoginState
}

