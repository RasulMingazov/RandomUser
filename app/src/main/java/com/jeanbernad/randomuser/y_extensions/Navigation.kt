package com.jeanbernad.randomuser.y_extensions

import android.app.Activity
import android.content.Intent
import com.jeanbernad.randomuser.y_ui.MainActivity

fun Activity.toMainActivity() {
    val intent = Intent(this, MainActivity::class.java)
    startActivity(intent)
    finish()
}