package com.integrame.app.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.integrame.app.core.data.local.entities.Student
import com.integrame.app.core.data.local.entities.Teacher
import com.integrame.app.core.data.local.entities.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User)

    @Insert
    suspend fun insertStudent(student: Student)

    @Insert
    suspend fun insertTeacher(teacher: Teacher)

    @Query("SELECT * FROM Students WHERE userID = :userID")
    fun getStudent(userID: Int) : Flow<Student>

    @Query("SELECT * FROM Teachers WHERE userID = :userID")
    fun getTeacher(userID: Int) : Flow<Teacher>
}
