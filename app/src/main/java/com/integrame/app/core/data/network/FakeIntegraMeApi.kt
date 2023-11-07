package com.integrame.app.core.data.network

import com.integrame.app.core.data.model.content.EContentAdaptationFormats
import com.integrame.app.core.data.model.content.EInteractionMethods
import com.integrame.app.login.data.network.NetworkAuthMethod
import com.integrame.app.login.data.network.NetworkIdentityCard
import com.integrame.app.login.data.network.NetworkImageAuthMethod
import com.integrame.app.login.data.network.NetworkTextAuthMethod
import kotlinx.coroutines.delay

private object FakeResources {
    val networkImages: List<NetworkImageContent> = listOf(
        NetworkImageContent(0, "ASD"),
        NetworkImageContent(1, "BSD"),
        NetworkImageContent(2, "BSD"),
        NetworkImageContent(3, "BSD")
    )

    val contentProfiles = List(15) { i->
        NetworkContentProfile(
            contentAdaptationFormats = listOf(EContentAdaptationFormats.Image),
            interactionMethods = listOf(EInteractionMethods.Default)
        )
    }

    val identityCardList = List(15) { i -> NetworkIdentityCard(
        userId = i,
        nickname = "Ismale Cpy Número $i",
        avatar = NetworkImageContent(
            id = i,
            altDescription = "Avatar"
        )
    )}

    val authMethodList = List(15) { i ->
        if (i%2 == 0)
            NetworkTextAuthMethod
        else
            NetworkImageAuthMethod(steps = 3, images = networkImages)
    }
}

object FakeIntegraMeApi : IntegraMeApi {
    override suspend fun getStudentsIdentityCards(): List<NetworkIdentityCard> {
        delay(1000)
        return FakeResources.identityCardList
    }

    override suspend fun getStudentIdentityCard(userId: Int): NetworkIdentityCard {
        delay(1000)
        return FakeResources.identityCardList[userId]
    }

    override suspend fun getStudentContentProfile(userId: Int): NetworkContentProfile {
        return FakeResources.contentProfiles[userId]
    }

    override suspend fun getStudentAuthMethod(userId: Int): NetworkAuthMethod {
        delay(1000)
        return FakeResources.authMethodList[userId]
    }
}
