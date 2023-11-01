package com.integrame.app.core.domain.repository

import com.integrame.app.core.data.model.session.Session
import com.integrame.app.core.util.Option

interface SessionRepository {
    suspend fun startSession(userID: Int, token: String)
    suspend fun getSession() : Option<Session>
    fun invalidateSession()
    suspend fun logout()
}