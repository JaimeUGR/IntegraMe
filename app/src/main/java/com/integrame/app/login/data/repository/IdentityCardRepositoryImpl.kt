package com.integrame.app.login.data.repository

import com.integrame.app.core.data.network.api.IntegraMeApi
import com.integrame.app.core.util.RequestResult
import com.integrame.app.login.data.model.IdentityCard
import com.integrame.app.login.data.network.toIdentityCard
import com.integrame.app.login.domain.repository.IdentityCardRepository
import retrofit2.HttpException

class IdentityCardRepositoryImpl(
    private val api: IntegraMeApi
): IdentityCardRepository {
    override suspend fun getStudentsIdentityCards(): RequestResult<List<IdentityCard>> {
        return try {
            RequestResult.Success(api.getStudentsIdentityCards())
        } catch (e: HttpException) {
            val statusCode = e.code()
            RequestResult.Error("Error code: $statusCode")
        } catch (e: Exception) {
            RequestResult.Error("Error: ${e.message ?: " desconocido"}")
        }
    }

    override suspend fun getIdentityCard(userId: Int) : RequestResult<IdentityCard> {
        return try {
            RequestResult.Success(api.getStudentIdentityCard(userId))
        } catch (e: HttpException) {
            val statusCode = e.code()
            RequestResult.Error("Error code: $statusCode")
        } catch (e: Exception) {
            RequestResult.Error("Error: ${e.message ?: " desconocido"}")
        }
    }
}
