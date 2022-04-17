package com.jeanbernad.randomuser.y_data_old.enteties

import com.jeanbernad.randomuser.y_data_old.enteties.info.Info
import com.jeanbernad.randomuser.y_data_old.enteties.result.Result
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

data class User(
    val info: Info,
    val results: MutableList<Result>
) {
    override fun toString(): String {
        return "${fullName()}\n${birthday()}\n${gender()}\n${phone()}\n${mail()}\n${country()}\n${city()}\n${fullAddress()}\n${this.results[0].picture.medium}"
    }

    fun fullName() =
        "${this.results[0].name.title} ${this.results[0].name.first} ${this.results[0].name.last}"

    fun fullAddress() =
        "${this.results[0].location.street.name}, ${this.results[0].location.street.number}"

    fun gender() = this.results[0].gender.capitalize(Locale.ROOT)

    fun phone() = this.results[0].phone

    fun mail() = this.results[0].email

    fun country() = this.results[0].location.country

    fun city() = this.results[0].location.city

    fun coordinates() = "(${this.results[0].location.coordinates.latitude}, ${this.results[0].location.coordinates.longitude})"

    fun birthday(): String {
        val birthday = this.results[0].dob.date.substring(0, this.results[0].dob.date.length - 1)
        val dateTime: LocalDateTime =
            LocalDateTime.parse(birthday, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS"))
        val format: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.mm.yyyy")
        return format.format(dateTime)
    }
}