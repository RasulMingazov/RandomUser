package com.jeanbernad.randomuser.utils

import android.app.Activity
import android.content.Intent
import com.jeanbernad.randomuser.ui.MainActivity

fun Activity.toMainActivity() {
    val intent = Intent(this, MainActivity::class.java)
    startActivity(intent)
    finish()
}