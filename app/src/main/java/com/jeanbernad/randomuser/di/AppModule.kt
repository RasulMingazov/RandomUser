package com.jeanbernad.randomuser.di

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jeanbernad.randomuser.data.enteties.User
import com.jeanbernad.randomuser.data.remote.RemoteDataSource
import com.jeanbernad.randomuser.data.remote.UserService
import com.jeanbernad.randomuser.data.repository.UserRepository
import com.jeanbernad.randomuser.ui.user.UserViewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val viewModelModule = module {
    single { UserViewModel<User>(get()) }
}

val serviceModule = module {
    fun provideUserService(retrofit: Retrofit) =
        retrofit.create(UserService::class.java)

    fun provideRemoteDataSource(userService: UserService) =
        RemoteDataSource(userService)

    single { provideUserService(get()) }
    single { provideRemoteDataSource(get()) }

}

val netModule = module {
    fun provideGson(): Gson =
        GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()

    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl("https://randomuser.me/api/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    single { provideGson() }
    single {
        provideRetrofit(get())
    }
}

val repositoryModule = module {
    fun provideRepository(remoteDataSource: RemoteDataSource) = UserRepository(remoteDataSource)

    single { provideRepository(get()) }
}