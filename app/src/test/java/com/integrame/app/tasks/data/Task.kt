package com.integrame.app.tasks.data

import com.integrame.app.core.data.model.content.DynamicContent
import com.integrame.app.core.data.model.content.RemoteImage
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Test
import org.junit.Assert.*

class TaskTest {
    @Test
    fun TestRewardSerialize() {
        val reward: DynamicContent = RemoteImage("ASD", 0, "")
        println(Json.encodeToString(reward))
    }
}