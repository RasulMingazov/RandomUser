package com.jeanbernad.randomuser.data.user

import com.jeanbernad.randomuser.core.Abstract
import com.jeanbernad.randomuser.data.user.remote.UserRemoteModel
import java.lang.Exception
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

interface ToUserDataMapper : Abstract.Mapper {
    fun map(
        userRemoteModel: UserRemoteModel
    ): UserData.Success

    fun map(
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
    ): UserData.Success

    fun map(exception: Exception): UserData.Fail

    class Base : ToUserDataMapper {
        override fun map(userRemoteModel: UserRemoteModel) =
            UserData.Success(
                fullName = "${userRemoteModel.name.title} ${userRemoteModel.name.first} ${userRemoteModel.name.last}",
                fullAddress = "${userRemoteModel.location.street.name}, ${userRemoteModel.location.street.number}",
                gender = userRemoteModel.gender.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.ROOT
                    ) else it.toString()
                },
                phone = userRemoteModel.phone,
                mail = userRemoteModel.email,
                country = userRemoteModel.location.country,
                city = userRemoteModel.location.city,
                coordinates = "(${userRemoteModel.location.coordinates.latitude}, ${userRemoteModel.location.coordinates.longitude})",
                birthday = DateTimeFormatter.ofPattern("dd.MM.yyyy")
                    .format(
                        LocalDateTime.parse(
                            userRemoteModel.dob.date.substring(
                                0,
                                userRemoteModel.dob.date.length - 1
                            ),
                            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS")
                        )
                    ),
                image = userRemoteModel.picture.large,
                thumbnail = userRemoteModel.picture.thumbnail
            )

        override fun map(
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
        ) = UserData.Success(
            fullName, fullAddress, gender, phone, mail, country, city, coordinates, birthday, image, thumbnail
        )

        override fun map(exception: Exception) = UserData.Fail(exception)
    }
}