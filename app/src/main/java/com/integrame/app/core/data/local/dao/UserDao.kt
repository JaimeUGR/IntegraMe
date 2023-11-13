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
