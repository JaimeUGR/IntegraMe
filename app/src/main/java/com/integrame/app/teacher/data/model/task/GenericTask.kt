package com.integrame.app.teacher.data.model.task

import com.integrame.app.core.data.model.content.ContentPack
import com.integrame.app.core.data.model.content.DynamicContent
import com.integrame.app.core.data.model.content.ImageContent

data class GenericTask(
    override val displayName: String,
    override val displayImage: ImageContent,
    val steps: List<GenericTaskStep>,
): Task() {

    // Métodos para realizar las actualizaciones en GenericTask
    fun setDisplayName(newDisplayName: String): GenericTask {
        return copy(displayName = newDisplayName)
    }

    fun setDisplayImage(newDisplayImage: ImageContent): GenericTask {
        return copy(displayImage = newDisplayImage)
    }

    fun updateSteps(newSteps: List<GenericTaskStep>): GenericTask {
        return copy(steps = newSteps)
    }
}

data class GenericTaskStep(
    val name: String,
    val content: ContentPack
)
