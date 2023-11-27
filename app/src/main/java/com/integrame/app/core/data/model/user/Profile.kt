package com.integrame.app.core.data.model.user

import com.integrame.app.core.data.local.entities.UserType
import com.integrame.app.core.data.model.content.ContentProfile
import com.integrame.app.core.data.model.content.ImageContent
import com.integrame.app.login.data.model.IdentityCard

sealed interface Profile {
    val userId: Int
}

// TODO: Añadir la serialización para el perfil del estudiante

data class StudentProfile(
    override val userId: Int,
    val name: String,
    val surnames: String,
    val nickname: String,
    val avatar: ImageContent,
    val contentProfile: ContentProfile
): Profile

data class TeacherProfile(
    override val userId: Int
): Profile
