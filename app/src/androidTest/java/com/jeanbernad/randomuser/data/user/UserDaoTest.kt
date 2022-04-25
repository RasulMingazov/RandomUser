package com.jeanbernad.randomuser.data.user

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.jeanbernad.randomuser.data.user.local.UserDao
import com.jeanbernad.randomuser.data.user.local.UserDatabase
import com.jeanbernad.randomuser.data.user.local.UserLocalModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.assertEquals


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class UserDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: UserDatabase
    private lateinit var dao: UserDao

    @Before
    fun setup() {
        database = UserDatabase.TestRoom(
            ApplicationProvider.getApplicationContext()
        )
        dao = database.userDao()
    }

    @Test
    fun insertUser() = runTest {
        val userLocalModel = UserLocalModel(10,"Rasul Mingazov", "23.04.2001", "Male", "302131", "kvmsf@gmail.com",
            "Russia", "Kazan", "A, 23", "(2.323, 3.3222)", "dkcmlsdc")
        dao.insert(userLocalModel)

        val allLocalUsers = dao.allUsers()

        assertThat(allLocalUsers).contains(userLocalModel)
    }

    @Test
    fun countUser() = runTest {
        val expected = dao.allUsers().size
        val actual = dao.countUsers()

        assertEquals(expected, actual)
    }
}