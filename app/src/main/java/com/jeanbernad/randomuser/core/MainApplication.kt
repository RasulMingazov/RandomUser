package com.jeanbernad.randomuser.core

import android.app.Application
import com.jeanbernad.randomuser.di.app.AppComponent
import com.jeanbernad.randomuser.di.app.AppDependenciesStore
import com.jeanbernad.randomuser.di.app.DaggerAppComponent

class MainApplication : Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().context(context = this).build()
        AppDependenciesStore.dependencies = appComponent
    }
}