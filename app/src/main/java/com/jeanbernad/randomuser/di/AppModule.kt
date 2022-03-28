package com.jeanbernad.randomuser.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jeanbernad.randomuser.data.local.AppDatabase
import com.jeanbernad.randomuser.data.local.UserDao
import com.jeanbernad.randomuser.data.remote.RemoteDataSource
import com.jeanbernad.randomuser.data.remote.UserService
import com.jeanbernad.randomuser.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
            .baseUrl("https://randomuser.me/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideUserService(retrofit: Retrofit): UserService = retrofit.create(UserService::class.java)

    @Singleton
    @Provides
    fun provideRemoteDataSource(userService: UserService) = RemoteDataSource(userService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideUserDao(db: AppDatabase) = db.userDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: RemoteDataSource,
                          localDataSource: UserDao) =
            UserRepository(remoteDataSource, localDataSource)
}