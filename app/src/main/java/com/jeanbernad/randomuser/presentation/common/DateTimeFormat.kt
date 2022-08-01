package com.jeanbernad.randomuser.presentation.common

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

interface DateTimeFormat {
    fun userDateToPresentation(date: String): String

    class Base : DateTimeFormat {
        override fun userDateToPresentation(date: String): String =
            DateTimeFormatter.ofPattern("dd.MM.yyyy")
                .format(
                    LocalDateTime.parse(
                        date.substring(
                            DATE_FIRST_INDEX,
                            date.length - 1
                        ),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS")
                    )
                )
    }

    companion object {
        private const val DATE_FIRST_INDEX = 0
    }
}