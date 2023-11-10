package com.integrame.app.core.data.network

import com.integrame.app.core.data.model.content.ImageContent
import com.integrame.app.core.data.model.content.RemoteImage
import kotlinx.serialization.Serializable

@Serializable
data class NetworkImageContent(
    val id: Int,
    val altDescription: String
)

fun NetworkImageContent.toImageContent() : ImageContent {
    return RemoteImage(
        imageUrl = "https://imgs.search.brave.com/cltbF8SKHppSqlmt9AYkhME57dJ3663AtJNlbWZv0Iw/rs:fit:860:0:0/g:ce/aHR0cHM6Ly9zdDIu/ZGVwb3NpdHBob3Rv/cy5jb20vMTIwNzk5/OS82MjcxL2kvNjAw/L2RlcG9zaXRwaG90/b3NfNjI3MTY0OTEt/c3RvY2stcGhvdG8t/Y29ja2VyZWwtYXZh/dGFyLmpwZw",
        //TODO: "$id",
        id = id,
        altDescription = altDescription
    )
}
