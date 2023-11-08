package com.integrame.app.login.data.repository

import com.integrame.app.core.data.network.IntegraMeApi
import com.integrame.app.core.util.MyResult
import com.integrame.app.login.data.model.IdentityCard
import com.integrame.app.login.data.network.toIdentityCard
import javax.inject.Inject

// TODO: Refactor
interface IdentityCardRepository {
    suspend fun getStudentsIdentityCards() : MyResult<List<IdentityCard>, Exception>
    suspend fun getIdentityCard(userId: Int) : MyResult<IdentityCard, Exception>
}

class IdentityCardRepositoryImpl @Inject constructor(
    private val api: IntegraMeApi
) : IdentityCardRepository {
    override suspend fun getStudentsIdentityCards(): MyResult<List<IdentityCard>, Exception> {
        return MyResult.Success(api.getStudentsIdentityCards().map { networkIdentityCard ->
            networkIdentityCard.toIdentityCard()
        })

        // TODO
        // Petición API
            // Actualizar local

        // Si falla
            // Utilizar local
    }

    override suspend fun getIdentityCard(userId: Int) : MyResult<IdentityCard, Exception> {
        return MyResult.Success(api.getStudentIdentityCard(userId).toIdentityCard())
    }
}