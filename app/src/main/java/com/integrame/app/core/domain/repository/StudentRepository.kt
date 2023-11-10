package com.integrame.app.core.domain.repository

import com.integrame.app.core.data.model.content.ContentProfile
import com.integrame.app.core.data.model.session.Session
import com.integrame.app.core.data.model.user.StudentProfile
import com.integrame.app.core.util.AuthRequestResult
import com.integrame.app.core.util.MyResult
import com.integrame.app.core.util.RequestResult
import com.integrame.app.login.data.model.AuthMethod

interface StudentRepository {
    suspend fun getProfile(session: Session): AuthRequestResult<StudentProfile>

    suspend fun getContentProfile(userId: Int): RequestResult<ContentProfile>

    suspend fun getAuthMethod(userId: Int): RequestResult<AuthMethod>

    // TODO:
    // - Implementar el repositorio de estudiantes con los métodos para sacar la información variada de un estudiante
    // - Implementar la pantalla de autenticación de imágenes (hay que añadir lo del image set y también el método de autenticación)
}
