package com.integrame.app.login.domain.repository

import com.integrame.app.core.util.RequestResult
import com.integrame.app.login.data.model.IdentityCard

interface IdentityCardRepository {
    suspend fun getStudentsIdentityCards() : RequestResult<List<IdentityCard>>
    suspend fun getIdentityCard(userId: Int) : RequestResult<IdentityCard>
}
