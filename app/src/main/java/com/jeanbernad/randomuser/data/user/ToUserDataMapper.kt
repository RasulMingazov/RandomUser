package com.jeanbernad.randomuser.data.user

import com.jeanbernad.randomuser.core.Abstract
import com.jeanbernad.randomuser.data.user.local.UserLocalModel
import com.jeanbernad.randomuser.data.user.remote.UserRemoteModel
import java.lang.Exception
import java.util.*
import javax.inject.Inject

interface ToUserDataMapper : Abstract.Mapper {
    fun map(
        userRemoteModel: UserRemoteModel
    ): UserData.Success

    fun map(
        userLocalModel: UserLocalModel
    ): UserData.Success

    fun map(exception: Exception): UserData.Fail

    class Base @Inject constructor() : ToUserDataMapper {
        override fun map(userRemoteModel: UserRemoteModel) =
            with(userRemoteModel) {
                UserData.Success(
                    fullName = "${name.title} ${name.first} ${name.last}",
                    fullAddress = "${location.street.name}, ${location.street.number}",
                    gender = gender.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.ROOT
                        ) else it.toString()
                    },
                    phone = phone,
                    mail = email,
                    country = location.country,
                    city = location.city,
                    coordinates =
                    "(${location.coordinates.latitude}, ${location.coordinates.longitude})",
                    birthday = dob.date,
                    image = picture.large,
                    thumbnail = picture.thumbnail,
                )
            }

        override fun map(
            userLocalModel: UserLocalModel
        ) = UserData.Success(
            fullName = userLocalModel.fullName,
            fullAddress = userLocalModel.fullAddress,
            gender = userLocalModel.gender,
            phone = userLocalModel.phone,
            mail = userLocalModel.mail,
            country = userLocalModel.country,
            city = userLocalModel.city,
            coordinates = userLocalModel.coordinates,
            birthday = userLocalModel.birthday,
            image = userLocalModel.image,
            thumbnail = userLocalModel.thumbnail
        )

        override fun map(exception: Exception) = UserData.Fail(exception)
    }
}