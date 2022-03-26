package com.jeanbernad.randomuser.data.enteties.result.location

import android.os.Parcelable
import androidx.room.Embedded
import kotlinx.android.parcel.Parcelize

data class Location(
        val city: String,
        val coordinates: Coordinates,
        val country: String,
        val postcode: String,
        val state: String,
        val street: Street,
        val timezone: Timezone
)