package com.jeanbernad.randomuser

import android.app.Application
import com.jeanbernad.randomuser.di.netModule
import com.jeanbernad.randomuser.di.repositoryModule
import com.jeanbernad.randomuser.di.serviceModule
import com.jeanbernad.randomuser.di.viewModelModule
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