package com.integrame.app.core.data.model.user

import com.integrame.app.core.data.local.entities.UserType
import com.integrame.app.core.data.model.content.ContentProfile
import com.integrame.app.login.data.model.IdentityCard

sealed interface Profile {
    val userId: Int
}

data class StudentProfile(
    override val userId: Int,
    val contentProfile: ContentProfile
): Profile

data class TeacherProfile(
    override val userId: Int
): Profile
