package com.jeanbernad.randomuser.core

import android.app.Application
import com.jeanbernad.randomuser.di.app_d.AppComponent
import com.jeanbernad.randomuser.di.app_d.AppDepsStore
import com.jeanbernad.randomuser.di.app_d.DaggerAppComponent

class MainApplication : Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().context(context = this).build()
        AppDepsStore.deps = appComponent
    }
}