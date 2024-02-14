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

package com.integrame.app.teacher.data.model.profile

import com.integrame.app.core.data.model.content.ContentProfile
import com.integrame.app.core.data.model.content.ImageContent
import com.integrame.app.core.data.model.content.InteractionMethods
import com.integrame.app.core.data.model.content.UserContentAdaptationFormats
import com.integrame.app.core.data.network.NetworkContentProfile
import com.integrame.app.login.data.model.AuthMethod
import com.integrame.app.login.data.model.StudentPassword
import java.net.PasswordAuthentication

data class StudentProfile(
    val name: String,
    val surnames: String,
    val nickname: String,
    val avatar: ImageContent,
    val networkContentProfile: NetworkContentProfile,
    val password: StudentPassword,
    val authMethod: AuthMethod
)
