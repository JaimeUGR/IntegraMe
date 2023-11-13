package com.integrame.app.login.data.network

import com.integrame.app.core.data.network.NetworkImageContent
import com.integrame.app.core.data.network.toImageContent
import com.integrame.app.login.data.model.IdentityCard
import kotlinx.serialization.Serializable

@Serializable
data class NetworkIdentityCard(
    val userId: Int,
    val nickname: String,
    val avatar: NetworkImageContent
)

fun NetworkIdentityCard.toIdentityCard() : IdentityCard {
    return IdentityCard(
        userId = userId,
        nickname = nickname,
        avatar = avatar.toImageContent()
    )
}
