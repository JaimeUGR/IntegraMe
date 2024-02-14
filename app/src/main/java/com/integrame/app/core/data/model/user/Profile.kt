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
