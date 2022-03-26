package com.jeanbernad.randomuser.data.enteties.result.location

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.android.parcel.Parcelize

data class Street(
        @ColumnInfo(name="streetName")
        val name: String,
        val number: Int
)