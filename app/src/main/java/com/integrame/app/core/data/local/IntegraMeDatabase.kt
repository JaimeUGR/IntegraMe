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

package com.integrame.app.core.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.integrame.app.core.data.local.entities.User
import com.integrame.app.core.data.local.entities.Student
import com.integrame.app.core.data.local.entities.Teacher
import com.integrame.app.core.data.local.dao.UserDao

@Database(
    entities = [
        User::class,
        Student::class,
        Teacher::class
    ],
    version = 1,
    exportSchema = false
)
abstract class IntegraMeDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao

    companion object {
        @Volatile private var Instance: IntegraMeDatabase? = null

        fun getDatabase(context: Context): IntegraMeDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, IntegraMeDatabase::class.java, "integrame_database")
                    .build()
                    .also { Instance = it }
            }
        }

        // NOTE: Solo para testing
        fun getInMemoryDatabase(context: Context): IntegraMeDatabase {
            return Instance ?: synchronized(this) {
                Room.inMemoryDatabaseBuilder(
                    context, IntegraMeDatabase::class.java).build()
                    .also { Instance = it }
            }
        }
    }
}
