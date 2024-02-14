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

import com.integrame.app.core.data.model.content.RemoteImage
import com.integrame.app.core.data.network.api.FakeIntegraMeApi
import com.integrame.app.core.util.RequestResult
import com.integrame.app.login.data.model.ImageAuthMethod
import com.integrame.app.login.data.network.NetworkTextAuthMethod
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.Assert.*

class StudentRepositoryTest {

    val studentRepositoryImpl = StudentRespositoryImpl(FakeIntegraMeApi)

    @Test
    fun TestGetTextAuthMethod() = runTest {
        assertEquals(studentRepositoryImpl.getAuthMethod(0), RequestResult.Success(NetworkTextAuthMethod.toAuthMethod()))
    }

    @Test
    fun TestGetImageAuthMethod() = runTest {
        val authMethod = ImageAuthMethod(
            steps = 3,
            imageList = listOf(
                RemoteImage(
                    imageUrl = "0",
                    id = 0,
                    altDescription = "ASD"
                ),
                RemoteImage(
                    imageUrl = "1",
                    id = 1,
                    altDescription = "ASD"
                )
            )
        )

        assertEquals(studentRepositoryImpl.getAuthMethod(1), RequestResult.Success(authMethod))
    }
}