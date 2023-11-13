package com.integrame.app.login.data.network

import com.integrame.app.login.data.model.StudentPassword
import kotlinx.serialization.Serializable

@Serializable
data class SignInTeacherRequest(val nickname: String, val password: String)

@Serializable
data class SignInStudentRequest(val userId: Int, val password: StudentPassword)
