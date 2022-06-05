package com.jeanbernad.randomuser.di.user

import com.jeanbernad.randomuser.data.user.BaseUserRepository
import com.jeanbernad.randomuser.data.user.ToUserDataMapper
import com.jeanbernad.randomuser.data.user.UserDataToDomainMapper
import com.jeanbernad.randomuser.data.user.all.UsersDataToDomainMapper
import com.jeanbernad.randomuser.data.user.all.local.UsersLocalMapper
import com.jeanbernad.randomuser.data.user.local.*
import com.jeanbernad.randomuser.data.user.remote.UserRemoteDataSource
import com.jeanbernad.randomuser.domain.user.*
import com.jeanbernad.randomuser.domain.user.all.BaseUsersDataToDomainMapper
import com.jeanbernad.randomuser.domain.user.all.UsersDomain
import com.jeanbernad.randomuser.domain.user.all.UsersDomainToPresentationMapper
import com.jeanbernad.randomuser.presentation.user.BaseUserDomainToPresentationMapper
import com.jeanbernad.randomuser.presentation.user.UserPresentationModel
import com.jeanbernad.randomuser.presentation.user.all.BaseUsersDomainToPresentationMapper
import com.jeanbernad.randomuser.presentation.user.all.UsersPresentationModel
import dagger.Binds
import dagger.Module

@Module
interface UserBindModule {
    @Binds
    fun bindUserRepository(userRepository: BaseUserRepository<UserDomain, UsersDomain>): UserRepository<UserDomain, UsersDomain>

    @Binds
    fun bindUserInteractor(userInteractor: UserInteractor.Base): UserInteractor

    @Binds
    fun bindUserDataToDomainMapper(userDataToDomainMapper: BaseUserDataToDomainMapper): UserDataToDomainMapper<UserDomain>

    @Binds
    fun bindUsersDataToDomainMapper(usersDataToDomainMapper: BaseUsersDataToDomainMapper): UsersDataToDomainMapper<UsersDomain>

    @Binds
    fun bindUsersLocalMapper(usersLocalMapper: UsersLocalMapper.Base): UsersLocalMapper

    @Binds
    fun bindUserRemoteDataSource(userRemoteDataSource: UserRemoteDataSource.Base): UserRemoteDataSource

    @Binds
    fun bindUserLocalDataSource(userLocalDataSource: UserLocalDataSource.Base): UserLocalDataSource

    @Binds
    fun bindToUserLocalMapper(speciesDataToDbMapper: ToUserLocalMapper.Base): ToUserLocalMapper<UserLocalModel>

    @Binds
    fun bindToUserDataMapper(toSpeciesDataMapper: ToUserDataMapper.Base): ToUserDataMapper

    @Binds
    fun bindUserDomainToPresentationMapper(userDomainToPresentationMapper: BaseUserDomainToPresentationMapper): UserDomainToPresentationMapper<UserPresentationModel>

    @Binds
    fun bindUsersDomainToPresentationMapper(usersDomainToPresentationMapper: BaseUsersDomainToPresentationMapper): UsersDomainToPresentationMapper<UsersPresentationModel>
}