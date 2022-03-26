package com.jeanbernad.randomuser.data.enteties.result

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.android.parcel.Parcelize

data class Id(
        val name: String,
        val value: String
)