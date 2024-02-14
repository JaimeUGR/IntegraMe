/*
 *
 * Copyright (c) IntegraMe - Champions 2024 - All rights reserved
 *
 *  You may use, distribute and modify this code under the terms of the
 *  Creative Commons Attribution-NonCommercial 4.0 International (CC BY-NC 4.0) license.
 *
 *  You should have received a copy of the CC BY-NC 4.0 license. Otherwise, read
 *  the full license text here: https://creativecommons.org/licenses/by-nc/4.0/legalcode
 *
 *  This file belongs to the project IntegraMe: https://github.com/JaimeUGR/IntegraMe
 */

package com.integrame.app.login.data.network

import com.integrame.app.login.data.model.StudentPassword
import kotlinx.serialization.Serializable

@Serializable
data class SignInTeacherRequest(val nickname: String, val password: String)

@Serializable
data class SignInStudentRequest(val userId: Int, val password: StudentPassword)
