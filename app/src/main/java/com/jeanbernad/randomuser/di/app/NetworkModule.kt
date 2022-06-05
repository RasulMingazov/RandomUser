package com.jeanbernad.randomuser.di.app

import com.google.gson.Gson
import com.jeanbernad.randomuser.data.user.remote.UserService
import com.jeanbernad.randomuser.di.user.network.UserNetworkModule
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [UserNetworkModule::class])
class NetworkModule {

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
            .baseUrl("https://randomuser.me/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}