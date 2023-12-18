package com.integrame.app.tasks.data.network

import kotlinx.serialization.Serializable

@Serializable
data class NetworkPostMaterialRequestDelivered(
    val isDelivered: Boolean
)
