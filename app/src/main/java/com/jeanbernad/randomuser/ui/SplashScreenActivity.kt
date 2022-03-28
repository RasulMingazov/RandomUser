package com.jeanbernad.randomuser.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.jeanbernad.randomuser.R
import com.jeanbernad.randomuser.utils.toMainActivity

class SplashScreenActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        setContentView(R.layout.activity_splash_screen)

        Handler(Looper.getMainLooper()).postDelayed({
            toMainActivity()
        }, splashScreenDuration())
    }

    private fun splashScreenDuration() = 1000L
}