package com.integrame.app.core.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Teachers",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = arrayOf("userID"),
            childColumns = arrayOf("userID")
        )
    ]
)
data class Teacher(
    @PrimaryKey val userID: Int,
    val name: String
)
