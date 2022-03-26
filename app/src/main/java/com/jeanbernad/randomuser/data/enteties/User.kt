package com.jeanbernad.randomuser.data.enteties

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jeanbernad.randomuser.data.enteties.info.Info
import com.jeanbernad.randomuser.data.enteties.result.Result
import kotlinx.android.parcel.Parcelize


data class User(
        val info: Info,
        val results: MutableList<Result>
)