package com.jeanbernad.randomuser.y_data_old.enteties.result.location

data class Location(
    val city: String,
    val coordinates: Coordinates,
    val country: String,
    val postcode: String,
    val state: String,
    val street: Street,
    val timezone: Timezone
)