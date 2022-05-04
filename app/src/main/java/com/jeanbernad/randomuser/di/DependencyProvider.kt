package com.jeanbernad.randomuser.di

import android.content.Context
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jeanbernad.randomuser.core.ResourceProvider
import com.jeanbernad.randomuser.data.user.*
import com.jeanbernad.randomuser.data.user.local.*
import com.jeanbernad.randomuser.data.user.remote.UserRemoteDataSource
import com.jeanbernad.randomuser.data.user.remote.UserService
import com.jeanbernad.randomuser.di.app.AppModuleProvider
import com.jeanbernad.randomuser.di.app.RemoteModuleProvider
import com.jeanbernad.randomuser.di.user.*
import com.jeanbernad.randomuser.domain.ErrorDomainMapper
import com.jeanbernad.randomuser.domain.user.*
import com.jeanbernad.randomuser.presentation.ErrorPresentationMapper
import com.jeanbernad.randomuser.presentation.user.BaseUserDomainToPresentationMapper
import com.jeanbernad.randomuser.presentation.user.UserPresentationModel
import com.jeanbernad.randomuser.presentation.user.UserViewModel
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


interface DependencyProvider<T> {
    fun provide()

    class Koin(private val context: Context) : DependencyProvider<Module> {
        override fun provide() {
            startKoin {
                androidContext(context)
                androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
                modules(
                    listOf(
                        AppModuleProvider.Koin(context).appModule,
                        RemoteModuleProvider.Koin(context).remoteModule,
                        UserRemoteModuleProvider.Koin().userRemoteModule,
                        UserLocalModuleProvider.Koin().userLocalModule,
                        UserDataModuleProvider.Koin().userDataModule,
                        UserDomainModuleProvider.Koin().userDomainModule,
                        UserPresentationModuleProvider.Koin().userPresentationModule
                    )
                )
            }
        }
    }
}