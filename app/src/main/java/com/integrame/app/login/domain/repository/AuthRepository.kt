package com.integrame.app.login.domain.repository

import com.integrame.app.core.util.AuthRequestResult
import com.integrame.app.login.data.model.StudentPassword

interface AuthRepository {
    suspend fun signInTeacher(nickname: String, password: String) : AuthRequestResult<Unit>
    suspend fun signInStudent(userId: Int, password: StudentPassword) : AuthRequestResult<Unit>
}
