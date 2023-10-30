package com.integrame.app.core.data.repository

import com.integrame.app.core.data.model.session.Session
import com.integrame.app.core.util.Option
import javax.inject.Inject

interface ISessionRepository {

}

class SessionRepository @Inject constructor(

) : ISessionRepository {
    suspend fun getSession() : Option<Session> {
        return Option.of(Session(0, "", 0, 0))
    }

    suspend fun logout() {

    }
}