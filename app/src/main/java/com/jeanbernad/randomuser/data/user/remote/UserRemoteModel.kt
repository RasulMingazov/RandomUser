package com.jeanbernad.randomuser.data.user.remote

import com.jeanbernad.randomuser.core.Abstract
import com.jeanbernad.randomuser.data.user.ToUserDataMapper
import com.jeanbernad.randomuser.data.user.UserData
import com.jeanbernad.randomuser.data.user.remote.entity.result.*
import com.jeanbernad.randomuser.data.user.remote.entity.result.location.Location

data class UserRemoteModel(
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
) : Abstract.Object<UserData, ToUserDataMapper> {
    override fun map(mapper: ToUserDataMapper) =
        mapper.map(this)
}