package com.integrame.app.core.data.repository

import android.content.Context
import android.os.Build
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.integrame.app.core.data.local.entities.UserType
import com.integrame.app.core.data.model.session.Session
import com.integrame.app.core.domain.repository.SessionRepository
import com.integrame.app.core.util.Option
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext

class SessionRepositoryImpl(
    private val context: Context
) : SessionRepository {

    private val sessionPreferences = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        EncryptedSharedPreferences.create(
            "sessionPreferences",
            MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    } else {
        context.getSharedPreferences("sessionPreferences", Context.MODE_PRIVATE)
    }

    private val sessionMutex = Mutex()
    private var session: Option<Session> = Option.None

    companion object {
        val USER_ID_KEY = "user_id"
        val USER_TYPE_KEY = "user_type"
        val TOKEN_KEY = "token"
    }

    init {
        runBlocking {
            loadSession()
        }
    }

    override suspend fun loadSession() {
        withContext(Dispatchers.IO) {
            sessionMutex.withLock {
                val userId = sessionPreferences.getInt(USER_ID_KEY, -1)
                val userTypeString = sessionPreferences.getString(USER_TYPE_KEY, null)
                val token = sessionPreferences.getString(TOKEN_KEY, null)

                session = if (userId != -1 && !token.isNullOrBlank() && !userTypeString.isNullOrBlank()) {
                    Option.Some(Session(
                        userId = userId,
                        token = token,
                        userType = UserType.valueOf(userTypeString)
                    )
                    )
                } else {
                    Option.None
                }
            }
        }
    }

    override suspend fun startSession(session: Session) {
        sessionPreferences.edit {
            putInt(USER_ID_KEY, session.userId)
            putString(USER_TYPE_KEY, session.userType.toString())
            putString(TOKEN_KEY, session.token)
        }

        sessionMutex.withLock {
            this.session = Option.Some(session)
        }
    }

    override suspend fun getSession(): Option<Session> {
        return sessionMutex.withLock {
            session
        }
    }

    override suspend fun signOut() {
        sessionPreferences.edit {
            remove(USER_ID_KEY)
            remove(USER_TYPE_KEY)
            remove(TOKEN_KEY)
        }

        sessionMutex.withLock {
            session = Option.None
        }
    }

    // TODO: Mantiene la sesión activa pero la marca como inválida
    override suspend fun invalidateSession() {

    }
}
