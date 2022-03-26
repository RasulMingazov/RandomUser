package com.jeanbernad.randomuser.data.enteties.result

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.android.parcel.Parcelize

data class Registered(
        val age: Int,
        val date: String
)