package com.jeanbernad.randomuser.core

import android.content.Context
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jeanbernad.randomuser.data.user.*
import com.jeanbernad.randomuser.data.user.remote.UserService
import com.jeanbernad.randomuser.domain.user.UserDomain
import com.jeanbernad.randomuser.domain.user.UserDomainToPresentationMapper
import com.jeanbernad.randomuser.domain.user.UserInteractor
import com.jeanbernad.randomuser.presentation.user.BaseUserDomainToPresentationMapper
import com.jeanbernad.randomuser.presentation.user.UserPresentationModel
import com.jeanbernad.randomuser.presentation.user.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val appModule = module {

    fun provideResourceManager(context: Context): ResourceProvider = ResourceProvider.Base(context)

    single<ResourceProvider> { provideResourceManager(get()) }

    fun provideErrorPresentationMapper(resourceProvider: ResourceProvider) =
        ErrorPresentationMapper.Base(resourceProvider)

    single<ErrorPresentationMapper> { provideErrorPresentationMapper(get()) }


    fun provideGson(): Gson =
        GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()

    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl("https://randomuser.me/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    single { provideGson() }
    single { provideRetrofit(get()) }

    fun provideUserService(retrofit: Retrofit) = retrofit.create(UserService::class.java)
    factory { provideUserService(get()) }


    fun provideUserRemoteDataSource(
        service: UserService
    ) = UserRemoteDataSource.Base(service)
    single<UserRemoteDataSource> { provideUserRemoteDataSource(get()) }


    fun provideToUserMapper() = ToUserMapper.Base()
    factory <ToUserMapper> { provideToUserMapper() }


    fun provideUserDataToDomainMapper() = BaseUserDataToDomainMapper()
    factory <UserDataToDomainMapper<UserDomain>> { provideUserDataToDomainMapper() }

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


    fun provideUserInteractor(
        userRepository: UserRepository<UserDomain>
    ) = UserInteractor.Base(userRepository)
    single<UserInteractor> { provideUserInteractor(get()) }

    fun provideUserDomainToPresentationMapper(errorPresentationMapper: ErrorPresentationMapper) =
        BaseUserDomainToPresentationMapper(errorPresentationMapper)
    factory<UserDomainToPresentationMapper<UserPresentationModel>> {
        provideUserDomainToPresentationMapper(
            get()
        )
    }

    viewModel { UserViewModel(get(), get()) }

}

val netModule = module {
}

val viesModelModule = module {

}