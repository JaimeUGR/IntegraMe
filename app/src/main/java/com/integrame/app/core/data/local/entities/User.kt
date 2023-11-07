package com.integrame.app.core.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class UserType {
    Student,
    Teacher
}

@Entity(tableName = "Users")
data class User(
    @PrimaryKey val userId: Int,
    val userType: UserType
)
