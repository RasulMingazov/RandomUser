package com.jeanbernad.randomuser.core

import android.content.Context
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jeanbernad.randomuser.data.user.*
import com.jeanbernad.randomuser.data.user.local.*
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


interface DependencyProvider<T> {
    fun provide()
    val appModule: T
    val remoteModule: T
    val cacheModule: T
    val dataModule: T
    val domainModule: T
    val presentationModule: T

    class Koin(private val context: Context) : DependencyProvider<Module> {
        override fun provide() {
            startKoin {
                androidContext(context)
                androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
                modules(
                    listOf(
                        appModule,
                        remoteModule,
                        cacheModule,
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

        override val cacheModule: Module
            get() = module {

                fun provideUserDatabase(context: Context) = UserDatabase.BaseRoom(context)
                single<UserDatabase> { provideUserDatabase(get()) }

                fun provideUserDao(userDatabase: UserDatabase) = userDatabase.userDao()
                single { provideUserDao(get()) }

                fun provideToUserLocalMapper() = ToUserLocalMapper.Base()
                factory<ToUserLocalMapper<UserLocalModel>> {provideToUserLocalMapper()}

                fun provideLocalDataSource(userDao: UserDao) = UserLocalDataSource.Base(userDao)
                factory<UserLocalDataSource> {provideLocalDataSource(get())}
            }

        override val dataModule: Module
            get() = module {
                fun provideToUserMapper() = ToUserMapper.Base()
                factory<ToUserMapper> { provideToUserMapper() }

                fun provideUserDataToDomainMapper(
                    errorDomainMapper: ErrorDomainMapper
                ) = BaseUserDataToDomainMapper(errorDomainMapper)
                factory<UserDataToDomainMapper<UserDomain>> { provideUserDataToDomainMapper(get()) }

                fun provideUserRepository(
                    userRemoteDataSource: UserRemoteDataSource,
                    userLocalDataSource: UserLocalDataSource,
                    toUserMapper: ToUserMapper,
                    toUserLocalMapper: ToUserLocalMapper<UserLocalModel>,
                    mapper: UserDataToDomainMapper<UserDomain>
                ) = BaseUserRepository(
                    userRemoteDataSource,
                    userLocalDataSource,
                    toUserMapper,
                    toUserLocalMapper,
                    mapper
                )
                single<UserRepository<UserDomain>> { provideUserRepository(get(), get(), get(), get(), get()) }

            }
        override val domainModule: Module
            get() = module {

                fun provideErrorDomainMapper() =
                    ErrorDomainMapper.Base()
                single<ErrorDomainMapper> { provideErrorDomainMapper() }

                fun provideUserInteractor(
                    userRepository: UserRepository<UserDomain>
                ) = UserInteractor.Base(userRepository)

                single<UserInteractor> { provideUserInteractor(get()) }
            }
        override val presentationModule: Module
            get() = module {

                fun provideErrorPresentationMapper(resourceProvider: ResourceProvider) =
                    ErrorPresentationMapper.Base(resourceProvider)
                single<ErrorPresentationMapper> { provideErrorPresentationMapper(get()) }

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