package com.integrame.app.core.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.integrame.app.core.data.model.session.Session
import com.integrame.app.core.domain.repository.SessionRepository
import com.integrame.app.core.util.Option
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

val Context.sessionDataStore: DataStore<Preferences> by preferencesDataStore(name = "session")

class SessionRepositoryImpl(
    private val context: Context
) : SessionRepository {

    val sessionFlow: Flow<Option<Session>> = context.sessionDataStore.data
        .map { preferences ->
            val user = preferences[USER_KEY]
            val token = preferences[TOKEN_KEY]
            if (user != null && !token.isNullOrBlank()) {
                Option.Some(Session(userID = user, token = token, 0, 0))
            } else {
                Option.None
            }
        }
        .flowOn(Dispatchers.IO)
        .onStart { emit(Option.None) }

    companion object {
        val TOKEN_KEY = stringPreferencesKey("auth_token")
        val USER_KEY = intPreferencesKey("auth_user")
    }

    override suspend fun startSession(userID: Int, token: String) {
        context.sessionDataStore.edit { preferences ->
            preferences[USER_KEY] = userID
            preferences[TOKEN_KEY] = token
        }
    }

    override suspend fun getSession() : Option<Session> {
        return sessionFlow.firstOrNull() ?: Option.None
    }

    // NOTE:
    // Debería haber un flujo que, si la sesión se invalida por cualquier razón, se emita un bool
    // que en la UI lo mande a la pantalla desde la que se hace el logout
    override fun invalidateSession() {

    }

    // Cuando se llama, se limpia la sesión en cache
    override suspend fun logout() {
        context.sessionDataStore.edit { preferences ->
            preferences.remove(USER_KEY)
            preferences.remove(TOKEN_KEY)
        }
    }
}
