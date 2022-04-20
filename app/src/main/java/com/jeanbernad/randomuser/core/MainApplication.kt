package com.jeanbernad.randomuser.core

import android.app.Application
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jeanbernad.randomuser.BuildConfig
import com.jeanbernad.randomuser.data.remote.UserService
import com.jeanbernad.randomuser.y_di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            modules()
        }
    }
}