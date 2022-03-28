package com.jeanbernad.randomuser.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jeanbernad.randomuser.data.enteties.MinimalUser

@Dao
interface UserDao {

    @Query("SELECT * FROM users ORDER BY RANDOM() LIMIT 1")
    fun user(): LiveData<MinimalUser>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: MinimalUser)


}