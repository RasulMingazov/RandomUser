package com.jeanbernad.randomuser.data.enteties.result

import com.jeanbernad.randomuser.data.enteties.result.location.Location

data class Result(
        val id: Id,
        val cell: String,
        val dob: Dob,
        val email: String,
        val gender: String,
        val location: Location,
        val login: Login,
        val name: Name,
        val nat: String,
        val phone: String,
        val picture: Picture,
        val registered: Registered
)