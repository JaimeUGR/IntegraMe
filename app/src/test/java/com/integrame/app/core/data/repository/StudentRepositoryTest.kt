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