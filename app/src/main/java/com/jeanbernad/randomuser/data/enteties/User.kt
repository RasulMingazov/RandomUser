package com.jeanbernad.randomuser.data.enteties

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jeanbernad.randomuser.data.enteties.info.Info
import com.jeanbernad.randomuser.data.enteties.result.Result
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@Entity(tableName = "users")
data class MinimalUser(
        @PrimaryKey(autoGenerate = true)
        val id: Long = 0,
        val fullName: String = "",
        val birthday: String = "",
        val gender: String = "",
        val phone: String = "",
        val mail: String =  "",
        val country: String = "",
        val city: String = "",
        val fullAddress: String = "",
        val coordinates: String = "",
        val picture: String = ""
)

data class User(
        val info: Info,
        val results: MutableList<Result>
) {

    fun minimalUser() = MinimalUser(0, fullName(), birthday(), gender(), phone(), mail(), country(), city(), fullAddress(), coordinates(), this.results[0].picture.medium)
    override fun toString(): String {
        return "${fullName()}\n${birthday()}\n${gender()}\n${phone()}\n${mail()}\n${country()}\n${city()}\n${fullAddress()}\n${this.results[0].picture.medium}"
    }

    private fun fullName() = "${this.results[0].name.title} ${this.results[0].name.first} ${this.results[0].name.last}"

    private fun fullAddress() = "${this.results[0].location.street.name}, ${this.results[0].location.street.number}"

    private fun gender() = this.results[0].gender.capitalize(Locale.ROOT)

    private fun phone() = this.results[0].phone

    private fun mail() = this.results[0].email

    private fun country() = this.results[0].location.country

    private fun city() = this.results[0].location.city

    private fun coordinates() = "(${this.results[0].location.coordinates.latitude}, ${this.results[0].location.coordinates.longitude})"

    private fun birthday() : String {
        val birthday = this.results[0].dob.date.substring(0, this.results[0].dob.date.length - 1)
        val dateTime: LocalDateTime = LocalDateTime.parse(birthday, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS"))
        val format: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.mm.yyyy")
        return format.format(dateTime)
    }
}