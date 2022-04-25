package com.jeanbernad.randomuser.data.user.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Query("SELECT * FROM users ORDER BY RANDOM() LIMIT 1")
    fun user(): UserLocalModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: UserLocalModel)

    @Query("SELECT * FROM users")
    fun allUsers(): List<UserLocalModel>
}