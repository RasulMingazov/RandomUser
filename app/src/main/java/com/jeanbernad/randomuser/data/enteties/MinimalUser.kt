package com.jeanbernad.randomuser.data.enteties

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class MinimalUser(
        @PrimaryKey(autoGenerate = true)
        val id: Long = 0,
        val fullName: String = "",
        val birthday: String = "",
        val gender: String = "",
        val phone: String = "",
        val mail: String =  "",
        val country: String = "",
        val city: String = "",
        val fullAddress: String = "",
        val coordinates: String = "",
        val picture: String = ""
)