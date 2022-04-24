package com.jeanbernad.randomuser.core

import android.app.Application

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        DependencyProvider.Koin(this@MainApplication).provide()
    }
}