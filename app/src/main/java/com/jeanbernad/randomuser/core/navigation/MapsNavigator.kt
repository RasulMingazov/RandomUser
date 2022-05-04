package com.jeanbernad.randomuser.core.navigation

import android.content.Intent
import android.net.Uri
import java.util.*

interface MapsNavigator<T> {
    fun intoMaps(coordinates: List<String>): T

    class Base: MapsNavigator<Intent> {
        override fun intoMaps(coordinates: List<String>) =
            Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(
                    String.format(
                        Locale.ENGLISH,
                        "geo:${coordinates[0]},${coordinates[1]}"
                    )
                )
            }
    }
}