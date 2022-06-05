package com.jeanbernad.randomuser.data.user.local

import com.jeanbernad.randomuser.core.Abstract

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
        image: String
    ): T

    class Base: ToUserLocalMapper<UserLocalModel> {
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
            image: String
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
            image
        )
    }
}