package com.integrame.app.login.data.repository

import com.integrame.app.login.data.model.StudentPassword
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

interface IAuthRepository {
    suspend fun loginTeacher(user: String, password: String)
    suspend fun loginStudent(user: Int, password: StudentPassword)
}

class AuthRepository : IAuthRepository {
    override suspend fun loginTeacher(user: String, password: String) {

    }

    // TODO: Para el encode, lanzar hebra cpu
    override suspend fun loginStudent(user: Int, password: StudentPassword) {
        // construir la contraseña a partir del contenido dinámico que ha seleccionado el usuario
        val jsonPassword = Json.encodeToString(password)

        /*
            Idea de construcción: JSON
            [{ valor: X, tipoContenido: texto }, {...}].

            Luego en el backend, se lee el tipo de contenido y si es una imagen el valor se interpreta como un int
            y se entiende que es la id interna
         */
    }
}
