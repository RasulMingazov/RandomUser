package com.jeanbernad.randomuser.data.remote

import android.location.Location
import com.jeanbernad.randomuser.core.Abstract
import com.jeanbernad.randomuser.data.ToUserMapper
import com.jeanbernad.randomuser.data.UserData
import com.jeanbernad.randomuser.data.remote.entity.result.*

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
) : Abstract.Object<UserData, ToUserMapper> {
    override fun map(mapper: ToUserMapper) =
        mapper.map(id.value)
}
