package com.jeanbernad.randomuser.core.navigation

import android.content.ClipDescription
import android.content.Intent

interface MailNavigator<T> {
    fun intoMail(value: String): T

    class Base : MailNavigator<Intent> {
        override fun intoMail(value: String) =
            Intent(Intent.ACTION_SEND).apply {
                type = ClipDescription.MIMETYPE_TEXT_PLAIN
                putExtra(Intent.EXTRA_EMAIL, arrayListOf(value))
            }
    }
}