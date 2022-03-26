package com.jeanbernad.randomuser.extensions

import com.jeanbernad.randomuser.data.enteties.User
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun User.fullName() = "${this.results[0].name.title} ${this.results[0].name.first} ${this.results[0].name.last}"

fun User.fullAddress() = "${this.results[0].location.street.name}, ${this.results[0].location.street.number}"

fun User.gender() = this.results[0].gender.capitalize()

fun User.phone() = this.results[0].phone

fun User.mail() = this.results[0].email

fun User.country() = this.results[0].location.country

fun User.city() = this.results[0].location.city

fun User.birthday() : String {
    val birthday = this.results[0].dob.date.substring(0, this.results[0].dob.date.length - 1)
    val dateTime: LocalDateTime = LocalDateTime.parse(birthday, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS"))
    val format: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.mm.yyyy")
    return format.format(dateTime)
}