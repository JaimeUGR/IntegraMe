package com.integrame.app.teacher.data.model.task

import com.integrame.app.core.data.model.content.ContentPack
import com.integrame.app.core.data.model.content.DynamicContent
import com.integrame.app.core.data.model.content.ImageContent
import com.integrame.app.core.data.model.content.RemoteImage

data class GenericTask(
    override val displayName: String,
    override val displayImage: RemoteImage,
    val steps: List<GenericTaskStep>,
): Task() {

    // Métodos para realizar las actualizaciones en GenericTask
    fun setDisplayName(newDisplayName: String): GenericTask {
        return copy(displayName = newDisplayName)
    }

    fun setDisplayImage(newDisplayImage: RemoteImage): GenericTask {
        return copy(displayImage = newDisplayImage)
    }

    fun updateSteps(newSteps: List<GenericTaskStep>): GenericTask {
        return copy(steps = newSteps)
    }
}

data class GenericTaskStep(
    val tittle: String,
    val description: String,
    val content: ContentPack
)
