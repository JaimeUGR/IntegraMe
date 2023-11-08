package com.integrame.app.login.data.repository

import com.integrame.app.core.data.network.IntegraMeApi
import com.integrame.app.core.domain.repository.SessionRepository
import com.integrame.app.core.util.AuthRequestResult
import com.integrame.app.core.util.Option
import com.integrame.app.login.data.model.StudentPassword
import com.integrame.app.login.data.network.SignInStudentRequest
import com.integrame.app.login.data.network.SignInTeacherRequest
import com.integrame.app.login.domain.repository.AuthRepository
import retrofit2.HttpException
import javax.inject.Inject

class AuthRepositoryImpl (
    private val sessionRepository: SessionRepository,
    private val api: IntegraMeApi
) : AuthRepository {
    override suspend fun signInTeacher(nickname: String, password: String) : AuthRequestResult<Unit> {
        val request = SignInTeacherRequest(nickname, password)

        return try {
            val session = api.signInTeacher(request)
            sessionRepository.startSession(session)

            AuthRequestResult.Authorized(Unit)
        } catch (e: HttpException) {
            val statusCode = e.code()

            return if (statusCode == 401)
                AuthRequestResult.Unauthorized()
            else
                AuthRequestResult.Error("Error code: $statusCode")
        } catch (e: Exception) {
            AuthRequestResult.Error("Error: ${e.message ?: " desconocido"}")
        }
    }

    // TODO: Para el encode, lanzar hebra cpu
    override suspend fun signInStudent(userId: Int, password: StudentPassword) : AuthRequestResult<Unit> {
        val request = SignInStudentRequest(userId, password)

        return try {
            val session = api.signInStudent(request)
            sessionRepository.startSession(session)

            AuthRequestResult.Authorized(Unit)
        } catch (e: HttpException) {
            val statusCode = e.code()

            return if (statusCode == 401)
                AuthRequestResult.Unauthorized()
            else
                AuthRequestResult.Error("Error code: $statusCode")
        } catch (e: Exception) {
            AuthRequestResult.Error("Error: ${e.message ?: " desconocido"}")
        }
    }
}
