package com.jeanbernad.randomuser.core

import android.content.Context
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jeanbernad.randomuser.data.user.*
import com.jeanbernad.randomuser.data.user.remote.UserService
import com.jeanbernad.randomuser.domain.user.BaseUserDataToDomainMapper
import com.jeanbernad.randomuser.domain.user.UserDomain
import com.jeanbernad.randomuser.domain.user.UserDomainToPresentationMapper
import com.jeanbernad.randomuser.domain.user.UserInteractor
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


interface DependencyProvider {
    fun provide()
    val appModule: Module
    val remoteModule: Module
    val dataModule: Module
    val domainModule: Module
    val presentationModule: Module

    class Koin(private val context: Context) : DependencyProvider {
        override fun provide() {
            startKoin {
                androidContext(context)
                androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
                modules(
                    listOf(
                        appModule,
                        remoteModule,
                        dataModule,
                        domainModule,
                        presentationModule
                    )
                )
            }
        }

        override val appModule: Module
            get() = module {
                fun provideResourceManager(context: Context): ResourceProvider =
                    ResourceProvider.Base(context)

                single { provideResourceManager(get()) }

                fun provideErrorPresentationMapper(resourceProvider: ResourceProvider) =
                    ErrorPresentationMapper.Base(resourceProvider)

                single<ErrorPresentationMapper> { provideErrorPresentationMapper(get()) }

                fun provideGson(): Gson =
                    GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
                single { provideGson() }
            }
        override val remoteModule: Module
            get() = module {
                fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
                    .baseUrl("https://randomuser.me/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()

                single { provideRetrofit(get()) }

                fun provideUserService(retrofit: Retrofit) =
                    retrofit.create(UserService::class.java)
                factory { provideUserService(get()) }


                fun provideUserRemoteDataSource(
                    service: UserService
                ) = UserRemoteDataSource.Base(service)
                single<UserRemoteDataSource> { provideUserRemoteDataSource(get()) }
            }
        override val dataModule: Module
            get() = module {
                fun provideToUserMapper() = ToUserMapper.Base()
                factory<ToUserMapper> { provideToUserMapper() }


                fun provideUserDataToDomainMapper() = BaseUserDataToDomainMapper()
                factory<UserDataToDomainMapper<UserDomain>> { provideUserDataToDomainMapper() }

                fun provideUserRepository(
                    userRemoteDataSource: UserRemoteDataSource,
                    toUserMapper: ToUserMapper,
                    mapper: UserDataToDomainMapper<UserDomain>
                ) = BaseUserRepository(
                    userRemoteDataSource,
                    toUserMapper,
                    mapper
                )
                single<UserRepository<UserDomain>> { provideUserRepository(get(), get(), get()) }

            }
        override val domainModule: Module
            get() = module {
                fun provideUserInteractor(
                    userRepository: UserRepository<UserDomain>
                ) = UserInteractor.Base(userRepository)

                single<UserInteractor> { provideUserInteractor(get()) }
            }
        override val presentationModule: Module
            get() = module {
                fun provideUserDomainToPresentationMapper(
                    errorPresentationMapper: ErrorPresentationMapper
                ) = BaseUserDomainToPresentationMapper(errorPresentationMapper)

                factory<UserDomainToPresentationMapper<UserPresentationModel>> {
                    provideUserDomainToPresentationMapper(get())
                }

                viewModel { UserViewModel(get(), get()) }
            }
    }
}