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

package com.integrame.app.core.data.repository

import androidx.test.platform.app.InstrumentationRegistry
import com.integrame.app.core.data.local.entities.UserType
import com.integrame.app.core.data.model.session.Session
import com.integrame.app.core.util.Option
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.Assert.*

class SessionRepositoryTest {

    val sessionRespositoryImpl = SessionRepositoryImpl(InstrumentationRegistry.getInstrumentation().targetContext)
    val session = Session(0, "TEST_TOKEN", UserType.Student)

    @Test
    fun TestGetSession() = runTest {
        assertEquals(sessionRespositoryImpl.getSession(), Option.None)
        sessionRespositoryImpl.loadSession()
        assertEquals(sessionRespositoryImpl.getSession(), Option.None)
        sessionRespositoryImpl.startSession(session)
        assertEquals(sessionRespositoryImpl.getSession(), Option.Some<Session>(session))
    }

    @Test
    fun TestSignOut() = runTest {
        sessionRespositoryImpl.startSession(session)
        assertEquals(sessionRespositoryImpl.getSession(), Option.Some<Session>(session))
        sessionRespositoryImpl.signOut()
        assertEquals(sessionRespositoryImpl.getSession(), Option.None)
    }
}
