package com.integrame.app.activities.data.model

import com.integrame.app.core.data.model.content.DynamicContent

data class ActivityCard(
    val representation: List<DynamicContent>
)

data class ActivityData(
    val name: String,
    var isCompleted: Boolean,
    val steps: List<ActivityStepData>
)

data class ActivityStepData(
    val name: String,
    var isCompleted: Boolean
)
