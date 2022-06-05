package com.jeanbernad.randomuser.di.user


import com.jeanbernad.randomuser.data.user.BaseUserRepository
import com.jeanbernad.randomuser.data.user.ToUserMapper
import com.jeanbernad.randomuser.data.user.UserDataToDomainMapper
import com.jeanbernad.randomuser.data.user.local.*
import com.jeanbernad.randomuser.data.user.remote.UserRemoteDataSource
import com.jeanbernad.randomuser.data.user.remote.UserService
import com.jeanbernad.randomuser.domain.common.ErrorDomainMapper
import com.jeanbernad.randomuser.domain.user.BaseUserDataToDomainMapper
import com.jeanbernad.randomuser.domain.user.UserDomain
import com.jeanbernad.randomuser.domain.user.UserInteractor
import com.jeanbernad.randomuser.domain.user.UserRepository
import com.jeanbernad.randomuser.presentation.common.ErrorPresentationMapper
import com.jeanbernad.randomuser.presentation.user.BaseUserDomainToPresentationMapper
import dagger.Module
import dagger.Provides


@Module
class UserProvideModule {
    @Provides
    fun provideUserRepository(
        userRemoteDataSource: UserRemoteDataSource,
        userLocalDataSource: UserLocalDataSource,
        toUserMapper: ToUserMapper,
        toUserLocalMapper: ToUserLocalMapper<UserLocalModel>,
        mapper: UserDataToDomainMapper<UserDomain>
    ) = BaseUserRepository(
        userRemoteDataSource, userLocalDataSource, toUserMapper, toUserLocalMapper, mapper
    )

    @Provides
    fun provideUserInteractor(
        userRepository: UserRepository<UserDomain>,
    ) = UserInteractor.Base(userRepository)

    @Provides
    fun provideUserDataToDomainMapper(errorDomainMapper: ErrorDomainMapper) = BaseUserDataToDomainMapper(errorDomainMapper)

    @Provides
    fun provideUserRemoteDataSource(service: UserService) =
        UserRemoteDataSource.Base(service)

    @Provides
    fun provideUserLocalDataSource(
        userDao: UserDao
    ) = UserLocalDataSource.Base(userDao)

    @Provides
    fun provideUserDataToDbMapper() = ToUserLocalMapper.Base()

    @Provides
    fun provideUserRemoteMapper() = ToUserMapper.Base()

    @Provides
    fun provideUserDomainToUiMapper(errorPresentationMapper: ErrorPresentationMapper) =
        BaseUserDomainToPresentationMapper(errorPresentationMapper)
}