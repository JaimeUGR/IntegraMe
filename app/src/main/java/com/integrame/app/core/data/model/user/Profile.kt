package com.integrame.app.core.data.model.user

import com.integrame.app.core.data.local.entities.UserType
import com.integrame.app.core.data.model.content.ContentProfile
import com.integrame.app.core.data.model.content.ImageContent
import com.integrame.app.login.data.model.IdentityCard
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface Profile {
    val userId: Int
    val nickname: String
    val avatar: ImageContent
}

@Serializable
@SerialName("StudentProfile")
data class StudentProfile(
    override val userId: Int,
    override val nickname: String,
    override val avatar: ImageContent,
    val name: String,
    val surnames: String,
    val contentProfile: ContentProfile
): Profile

@Serializable
@SerialName("TeacherProfile")
data class TeacherProfile(
    override val userId: Int,
    override val nickname: String,
    override val avatar: ImageContent
): Profile
