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

package com.integrame.app.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.integrame.app.core.data.local.entities.Student
import com.integrame.app.core.data.local.entities.Teacher
import com.integrame.app.core.data.local.entities.User
import com.integrame.app.core.data.local.entities.UserType
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User)

    @Insert
    suspend fun insertStudent(student: Student)

    @Insert
    suspend fun insertTeacher(teacher: Teacher)

    @Query("SELECT userType FROM Users WHERE userId = :userId")
    suspend fun getUserType(userId: Int) : UserType

    @Query("SELECT * FROM Students WHERE userId = :userId")
    fun getStudent(userId: Int) : Flow<Student>

    @Query("SELECT * FROM Teachers WHERE userId = :userId")
    fun getTeacher(userId: Int) : Flow<Teacher>
}
