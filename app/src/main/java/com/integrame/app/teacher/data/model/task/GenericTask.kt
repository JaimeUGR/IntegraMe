/*
 *
 * Copyright (c) IntegraMe - Champions 2024 - All rights reserved
 *
 *  You may use, distribute and modify this code under the terms of the
 *  Creative Commons Attribution-NonCommercial 4.0 International (CC BY-NC 4.0) license.
 *
 *  You should have received a copy of the CC BY-NC 4.0 license. Otherwise, read
 *  the full license text here: https://creativecommons.org/licenses/by-nc/4.0/legalcode
 *
 *  This file belongs to the project IntegraMe: https://github.com/JaimeUGR/IntegraMe
 */

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
