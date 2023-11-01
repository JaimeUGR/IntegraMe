package com.integrame.app.login.domain.repository

import com.integrame.app.login.data.model.StudentPassword
import com.integrame.app.login.data.repository.LoginState

interface AuthRepository {
    suspend fun loginTeacher(user: String, password: String) : LoginState
    suspend fun loginStudent(user: Int, password: StudentPassword) : LoginState
}