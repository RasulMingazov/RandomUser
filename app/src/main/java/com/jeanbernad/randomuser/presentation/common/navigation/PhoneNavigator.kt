package com.jeanbernad.randomuser.presentation.common.navigation

import android.content.Intent
import android.net.Uri

interface PhoneNavigator<T> {
    fun intoPhone(number: String): T

    class Base : PhoneNavigator<Intent> {
        override fun intoPhone(number: String) =
            Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$number")
            }
    }
}