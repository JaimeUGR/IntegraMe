package com.integrame.app.login.data.repository

import com.integrame.app.core.data.local.entities.UserType
import com.integrame.app.core.data.network.api.IntegraMeApi
import com.integrame.app.core.data.network.toSession
import com.integrame.app.core.domain.repository.SessionRepository
import com.integrame.app.core.util.AuthRequestResult
import com.integrame.app.login.data.model.ImagePassword
import com.integrame.app.login.data.model.StudentPassword
import com.integrame.app.login.data.network.SignInStudentRequest
import com.integrame.app.login.data.network.SignInTeacherRequest
import com.integrame.app.login.domain.repository.AuthRepository
import retrofit2.HttpException

class AuthRepositoryImpl (
    private val sessionRepository: SessionRepository,
    private val api: IntegraMeApi
): AuthRepository {
    override suspend fun signInTeacher(nickname: String, password: String): AuthRequestResult<Unit> {
        val request = SignInTeacherRequest(nickname, password)

        return try {
            val session = api.signInTeacher(request).toSession(UserType.Teacher)
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

    // Comprueba si una contraseña de imágenes parcial es correcta
    override suspend fun checkImagePassword(userId: Int, password: ImagePassword): AuthRequestResult<Unit> {
        return try {
            api.checkImagePassword(userId, password)
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

    override suspend fun signInStudent(userId: Int, password: StudentPassword): AuthRequestResult<Unit> {
        val request = SignInStudentRequest(userId, password)

        return try {
            val session = api.signInStudent(request).toSession(UserType.Student)
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
