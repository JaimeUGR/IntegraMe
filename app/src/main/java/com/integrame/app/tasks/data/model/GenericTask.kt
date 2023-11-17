package com.integrame.app.tasks.data.model

import com.integrame.app.core.data.model.content.ContentPack
import com.integrame.app.core.data.model.content.DynamicContent
import com.integrame.app.core.data.model.content.ImageContent

data class GenericTask(
    override val displayName: String,
    override val displayImage: ImageContent,
    val steps: List<GenericTaskStep>,
    val reward: DynamicContent,
    var isCompleted: Boolean
): Task()

data class GenericTaskStep(
    val name: String,
    var isCompleted: Boolean,
    val content: ContentPack
)
