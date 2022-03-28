package com.jeanbernad.randomuser.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.jeanbernad.randomuser.data.enteties.MinimalUser
import com.jeanbernad.randomuser.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class UserDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: AppDatabase
    private lateinit var dao: UserDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.userDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertShoppingItem() = runBlockingTest {
        val minimalUser = MinimalUser(10,"Rasul Mingazov", "23.04.2001", "Male", "302131", "kvmsf@gmail.com",
            "Russia", "Kazan", "A, 23", "(2.323, 3.3222)", "dkcmlsdc")
        dao.insert(minimalUser)

        val allShoppingItems = dao.allUsers().getOrAwaitValue()

        assertThat(allShoppingItems).contains(minimalUser)
    }
}