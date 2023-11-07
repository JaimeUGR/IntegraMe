package com.integrame.app.core.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Students",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = arrayOf("userId"),
            childColumns = arrayOf("userId")
        )
    ]
)
data class Student(
    @PrimaryKey val userId: Int,
    val name: String,
    val surnames: String,
    val avatarUrl: String
    //val contentAdaptation: List<EContentAdaptationFormats>,
    //val interactionMethods: List<EInteractionMethods>
)
