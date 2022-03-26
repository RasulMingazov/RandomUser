package com.jeanbernad.randomuser.data.enteties.result

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Dob(
        val age: Int,
        val date: String
):Parcelable