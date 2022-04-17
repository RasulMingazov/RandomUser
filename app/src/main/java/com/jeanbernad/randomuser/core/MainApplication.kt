package com.jeanbernad.randomuser.core

import android.app.Application
import com.jeanbernad.randomuser.BuildConfig
import com.jeanbernad.randomuser.y_di.netModule
import com.jeanbernad.randomuser.y_di.repositoryModule
import com.jeanbernad.randomuser.y_di.serviceModule
import com.jeanbernad.randomuser.y_di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            modules(listOf(viewModelModule, repositoryModule, netModule, serviceModule))
        }
    }
}