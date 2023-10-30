package com.integrame.app.core.data.network

import com.integrame.app.login.data.model.IdentityCard

object FakeIntegraMeApi : IntegraMeApi {
    override fun getStudentIdentities(): List<IdentityCard> {
        TODO("Not yet implemented")
    }

}