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

package com.integrame.app.tasks.data.model

import com.integrame.app.core.data.model.content.DynamicContent
import com.integrame.app.core.data.model.content.ImageContent
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
enum class TaskType {
    GenericTask,
    MenuTask,
    MaterialTask
}

@Serializable
enum class TaskState {
    Failed,
    Pending,
    Completed
}

@Serializable
data class TaskCard(
    val taskId: Int,
    val taskState: TaskState,
    val displayName: String,
    val displayImage: ImageContent,
    val taskType: TaskType
)

@Serializable
abstract class Task {
    abstract val taskId: Int
    abstract val displayName: String
    abstract val displayImage: ImageContent
    abstract val reward: DynamicContent
}

@Serializable
sealed class TaskModel {
    abstract val taskId: Int
    abstract val displayName: String
    abstract val displayImage: ImageContent
    abstract val reward: DynamicContent
}