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