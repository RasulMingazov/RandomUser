package com.jeanbernad.randomuser.data.enteties.result

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class Name(
        val first: String,
        val last: String,
        val title: String
)