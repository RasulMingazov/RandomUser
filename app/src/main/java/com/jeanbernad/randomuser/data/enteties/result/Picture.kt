package com.jeanbernad.randomuser.data.enteties.result

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class Picture(
        val large: String,
        val medium: String,
        val thumbnail: String
)