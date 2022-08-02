package com.jeanbernad.randomuser.data.user.local

import com.jeanbernad.randomuser.core.Abstract
import javax.inject.Inject

interface ToUserLocalMapper<T>: Abstract.Mapper {
    fun mapToLocal(
        fullName: String,
        fullAddress: String,
        gender: String,
        phone: String,
        mail: String,
        country: String,
        city: String,
        coordinates: String,
        birthday: String,
        image: String,
        thumbnail: String
    ): T

    class Base @Inject constructor(): ToUserLocalMapper<UserLocalModel> {
        override fun mapToLocal(
            fullName: String,
            fullAddress: String,
            gender: String,
            phone: String,
            mail: String,
            country: String,
            city: String,
            coordinates: String,
            birthday: String,
            image: String,
            thumbnail: String
        ) = UserLocalModel(
            fullName,
            fullAddress,
            gender,
            phone,
            mail,
            country,
            city,
            coordinates,
            birthday,
            image,
            thumbnail
        )
    }
}