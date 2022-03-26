package com.jeanbernad.randomuser.data.enteties.result.location

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class Timezone(
        val description: String,
        val offset: String
)