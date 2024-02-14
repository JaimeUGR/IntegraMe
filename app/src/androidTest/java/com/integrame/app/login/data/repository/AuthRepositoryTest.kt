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

package com.integrame.app.login.data.repository

import androidx.test.platform.app.InstrumentationRegistry
import com.integrame.app.core.data.network.api.FakeIntegraMeApi
import com.integrame.app.core.data.repository.SessionRepositoryImpl
import com.integrame.app.core.domain.repository.SessionRepository
import com.integrame.app.core.util.AuthRequestResult
import com.integrame.app.login.data.model.ImagePassword
import com.integrame.app.login.data.model.TextPassword
import com.integrame.app.login.domain.repository.AuthRepository
import kotlinx.coroutines.test.runTest
import org.junit.Test

class AuthRepositoryTest {

    val authRepository: AuthRepository
    val sessionRepository: SessionRepository

    init {
        sessionRepository = SessionRepositoryImpl(InstrumentationRegistry.getInstrumentation().targetContext)
        authRepository = AuthRepositoryImpl(sessionRepository, FakeIntegraMeApi)
    }


    @Test
    fun TestSignInStudent_Text() = runTest {
        assert(authRepository.signInStudent(0, TextPassword("integrame")) is AuthRequestResult.Authorized)
    }

    // TODO: Añadir a los fake resources para testear con una lista de imágenes
    @Test
    fun TestSignInStudent_Image() = runTest {
        assert(authRepository.signInStudent(0, ImagePassword(listOf(0))) is AuthRequestResult.Authorized)
    }

    @Test
    fun TestSignInTeacher() = runTest {
        assert(authRepository.signInTeacher("francx11", "pollita") is AuthRequestResult.Unauthorized)
        assert(authRepository.signInTeacher("francx11", "pollito") is AuthRequestResult.Authorized)
    }
}
