package com.jeanbernad.randomuser.data.user.local

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.jeanbernad.randomuser.core.Abstract
import com.jeanbernad.randomuser.data.user.ToUserMapper
import com.jeanbernad.randomuser.data.user.UserData

@Entity(tableName = "users")
data class UserLocalModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val fullName: String = "",
    val fullAddress: String = "",
    val gender: String = "",
    val phone: String = "",
    val mail: String = "",
    val country: String = "",
    val city: String = "",
    val coordinates: String = "",
    val birthday: String = "",
    val image: String
) : Abstract.Object<UserData, ToUserMapper> {

    override fun map(mapper: ToUserMapper) = mapper.map(
        fullName, fullAddress, gender, phone, mail, country, city, coordinates, birthday, image
    )
    @Ignore
    constructor(
        fullName: String,
        fullAddress: String,
        gender: String,
        phone: String,
        mail: String,
        country: String,
        city: String,
        coordinates: String,
        birthday: String,
        image: String
    ) : this(
        0,
        fullName,
        fullAddress,
        gender,
        phone,
        mail,
        country,
        city,
        coordinates,
        birthday,
        image
    )
}