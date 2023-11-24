package com.integrame.app.tasks.data.model

import com.integrame.app.core.data.model.content.ContentPack
import com.integrame.app.core.data.model.content.DynamicContent
import com.integrame.app.core.data.model.content.ImageContent
import com.integrame.app.core.data.model.content.RemoteImage
import kotlinx.serialization.Serializable

@Serializable
data class GenericTask(
    override val displayName: String,
    override val displayImage: RemoteImage,
    val steps: List<GenericTaskStep>,
    val reward: DynamicContent,
    var isCompleted: Boolean
): Task()

@Serializable
data class GenericTaskStep(
    val name: String,
    var isCompleted: Boolean,
    val content: ContentPack
)
