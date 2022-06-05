package com.jeanbernad.randomuser.di.user


import com.jeanbernad.randomuser.data.user.BaseUserRepository
import com.jeanbernad.randomuser.data.user.ToUserDataMapper
import com.jeanbernad.randomuser.data.user.UserDataToDomainMapper
import com.jeanbernad.randomuser.data.user.all.UsersDataToDomainMapper
import com.jeanbernad.randomuser.data.user.all.local.UsersLocalMapper
import com.jeanbernad.randomuser.data.user.local.*
import com.jeanbernad.randomuser.data.user.remote.UserRemoteDataSource
import com.jeanbernad.randomuser.data.user.remote.UserService
import com.jeanbernad.randomuser.domain.common.ErrorDomainMapper
import com.jeanbernad.randomuser.domain.user.*
import com.jeanbernad.randomuser.domain.user.all.BaseUsersDataToDomainMapper
import com.jeanbernad.randomuser.domain.user.all.UsersDomain
import com.jeanbernad.randomuser.presentation.common.ErrorPresentationMapper
import com.jeanbernad.randomuser.presentation.user.BaseUserDomainToPresentationMapper
import com.jeanbernad.randomuser.presentation.user.UserPresentationModel
import com.jeanbernad.randomuser.presentation.user.all.BaseUsersDomainToPresentationMapper
import dagger.Module
import dagger.Provides


@Module
class UserProvideModule {
    @Provides
    fun provideUserRepository(
        userRemoteDataSource: UserRemoteDataSource,
        userLocalDataSource: UserLocalDataSource,
        toUserDataMapper: ToUserDataMapper,
        toUserLocalMapper: ToUserLocalMapper<UserLocalModel>,
        mapper: UserDataToDomainMapper<UserDomain>,
        usersLocalMapper: UsersLocalMapper,
        usersDataToDomainMapper: UsersDataToDomainMapper<UsersDomain>
    ) = BaseUserRepository(
        userRemoteDataSource,
        userLocalDataSource,
        toUserDataMapper,
        toUserLocalMapper,
        mapper,
        usersLocalMapper,
        usersDataToDomainMapper
    )

    @Provides
    fun provideUserInteractor(
        userRepository: UserRepository<UserDomain, UsersDomain>,
    ) = UserInteractor.Base(userRepository)

    @Provides
    fun provideUserDataToDomainMapper(errorDomainMapper: ErrorDomainMapper) =
        BaseUserDataToDomainMapper(errorDomainMapper)

    @Provides
    fun provideUsersDataToDomainMapper(
        errorDomainMapper: ErrorDomainMapper,
        userDataToDomainMapper: UserDataToDomainMapper<UserDomain>
    ) =
        BaseUsersDataToDomainMapper(errorDomainMapper, userDataToDomainMapper)

    @Provides
    fun provideUsersLocalMapper(toUserDataMapper: ToUserDataMapper): UsersLocalMapper.Base =
        UsersLocalMapper.Base(toUserDataMapper)

    @Provides
    fun provideUserRemoteDataSource(service: UserService) =
        UserRemoteDataSource.Base(service)

    @Provides
    fun provideUserLocalDataSource(
        userDao: UserDao
    ) = UserLocalDataSource.Base(userDao)

    @Provides
    fun provideToUserLocalMapper() = ToUserLocalMapper.Base()

    @Provides
    fun provideToUserDataMapper() = ToUserDataMapper.Base()

    @Provides
    fun provideUserDomainToPresentationMapper(errorPresentationMapper: ErrorPresentationMapper) =
        BaseUserDomainToPresentationMapper(errorPresentationMapper)

    @Provides
    fun provideUsersDomainToPresentationMapper(
        errorMapper: ErrorPresentationMapper,
        userMapper: UserDomainToPresentationMapper<UserPresentationModel>
    ) = BaseUsersDomainToPresentationMapper(errorMapper, userMapper)
}