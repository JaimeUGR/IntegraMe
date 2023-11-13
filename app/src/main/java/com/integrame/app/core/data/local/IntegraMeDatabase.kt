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
