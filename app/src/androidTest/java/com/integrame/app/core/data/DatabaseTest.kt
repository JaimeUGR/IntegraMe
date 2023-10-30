package com.integrame.app.core.data

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.integrame.app.core.data.local.IntegraMeDatabase
import com.integrame.app.core.data.local.entities.Teacher
import com.integrame.app.core.data.local.entities.User
import com.integrame.app.core.data.local.dao.UserDao
import com.integrame.app.core.data.local.entities.UserType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DatabaseTest {
    private lateinit var userDao: UserDao
    private lateinit var db: IntegraMeDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = IntegraMeDatabase.getInMemoryDatabase(context)
        userDao = db.userDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun addNewUser() = runTest {
        userDao.insert(User(25, UserType.Teacher))
        val teacher = Teacher(25, "")
        userDao.insertTeacher(teacher)

        assertEquals(teacher, userDao.getTeacher(25).first())
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test(expected = android.database.sqlite.SQLiteConstraintException::class)
    fun addNewTeacher() = runTest {
        userDao.insertTeacher(Teacher(9293923, ""))
    }
}
