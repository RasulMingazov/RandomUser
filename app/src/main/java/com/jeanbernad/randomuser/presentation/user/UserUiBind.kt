package com.jeanbernad.randomuser.presentation.user

import android.widget.ImageView
import android.widget.TextView
import com.jeanbernad.randomuser.core.Abstract
import com.jeanbernad.randomuser.presentation.common.LoaderImage

interface UserUiBind : Abstract.UiObjectBind {

    fun bindName(name: TextView)
    fun bindGender(gender: TextView)
    fun bindBirthday(birthday: TextView)
    fun bindPhone(phone: TextView)
    fun bindMail(mail: TextView)
    fun bindCountry(country: TextView)
    fun bindCity(city: TextView)
    fun bindAddress(address: TextView)
    fun bindCoordinates(coordinates: TextView)
    fun bindAvatar(
        loaderImage: LoaderImage,
        imageView: ImageView,
        start: () -> Unit,
        success: () -> Unit
    )

    fun bindError(error: TextView)
}