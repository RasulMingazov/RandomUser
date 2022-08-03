package com.jeanbernad.randomuser.presentation.user

import android.widget.TextView
import com.jeanbernad.randomuser.core.Abstract

interface UserUiBind: Abstract.UiObjectBind {
    fun bind(
        name: TextView,
        gender: TextView,
        birthday: TextView,
        phone: TextView,
        mail: TextView,
        country: TextView,
        city: TextView,
        address: TextView,
        coordinates: TextView
    )

    fun bind(
        error: TextView
    )
}