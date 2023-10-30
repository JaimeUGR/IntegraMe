package com.integrame.app.login.data.repository

import com.integrame.app.core.data.network.IntegraMeApi
import com.integrame.app.login.data.model.IdentityCard
import com.integrame.app.login.data.network.asIdentityCard
import javax.inject.Inject

interface IIdentityCardRepository {
    suspend fun getStudentsIdentityCards() : List<IdentityCard>
}

class IdentityCardRepository @Inject constructor(
    private val api: IntegraMeApi
) : IIdentityCardRepository {
    override suspend fun getStudentsIdentityCards(): List<IdentityCard> {
        return api.getStudentsIdentityCards().map { it.asIdentityCard() }

        // TODO
        // Petición API
            // Actualizar local

        // Si falla
            // Utilizar local
    }
}