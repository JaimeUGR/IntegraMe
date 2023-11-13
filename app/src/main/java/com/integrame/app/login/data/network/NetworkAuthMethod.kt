package com.integrame.app.login.data.network

import com.integrame.app.core.data.network.NetworkImageContent
import com.integrame.app.core.data.network.toImageContent
import com.integrame.app.login.data.model.AuthMethod
import com.integrame.app.login.data.model.ImageAuthMethod
import com.integrame.app.login.data.model.TextAuthMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface NetworkAuthMethod {
    fun toAuthMethod() : AuthMethod
}

@Serializable
@SerialName("TextAuth")
object NetworkTextAuthMethod : NetworkAuthMethod {
    override fun toAuthMethod(): AuthMethod {
        return TextAuthMethod
    }
}

@Serializable
@SerialName("ImageAuth")
data class NetworkImageAuthMethod(
    val steps: Int,
    val images: List<NetworkImageContent>
) : NetworkAuthMethod {
    override fun toAuthMethod(): AuthMethod {
        return ImageAuthMethod(steps, images.map { networkImageContent ->
            networkImageContent.toImageContent()
        })
    }
}
