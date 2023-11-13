package com.integrame.app.core.domain.repository

import com.integrame.app.core.data.model.user.TeacherProfile
import com.integrame.app.core.util.AuthRequestResult

interface TeacherRepository {
    suspend fun getProfile(userId: Int): AuthRequestResult<TeacherProfile>
}
