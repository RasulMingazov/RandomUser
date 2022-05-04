package com.jeanbernad.randomuser.core

import android.app.Application
import com.jeanbernad.randomuser.di.DependencyProvider

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        DependencyProvider.Koin(this@MainApplication).provide()
    }
}