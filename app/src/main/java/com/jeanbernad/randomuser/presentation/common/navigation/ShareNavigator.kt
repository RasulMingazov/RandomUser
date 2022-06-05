package com.jeanbernad.randomuser.presentation.common.navigation

import android.content.Intent

interface ShareNavigator<T> {
    fun intoShare(value: String): T

    class Base : ShareNavigator<Intent> {
        override fun intoShare(value: String) =
            Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, value)
                type = "text/plain"
            }
    }
}