package com.jeanbernad.randomuser.di.app

import android.content.Context
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jeanbernad.randomuser.data.user.remote.UserRemoteDataSource
import com.jeanbernad.randomuser.data.user.remote.UserService
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface RemoteModuleProvider<T> {
    val remoteModule: T

    class Koin(private val context: Context) : RemoteModuleProvider<Module> {
        override val remoteModule: Module
            get() = module {
                fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
                    .baseUrl("https://randomuser.me/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                single { provideRetrofit(get()) }

                fun provideGson(): Gson =
                    GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
                factory { provideGson() }

            }
    }
}