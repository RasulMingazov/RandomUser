package com.jeanbernad.randomuser.di.app

import com.google.gson.Gson
import com.jeanbernad.randomuser.BuildConfig
import com.jeanbernad.randomuser.di.user.network.UserRemoteModule
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [UserRemoteModule::class])
class RemoteModule {

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).build()

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.RANDOM_USER_API)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}