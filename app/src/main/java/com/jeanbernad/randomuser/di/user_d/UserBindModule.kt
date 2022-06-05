package com.jeanbernad.randomuser.di.user_d

import com.jeanbernad.randomuser.data.user.BaseUserRepository
import com.jeanbernad.randomuser.data.user.ToUserMapper
import com.jeanbernad.randomuser.data.user.UserDataToDomainMapper
import com.jeanbernad.randomuser.data.user.local.*
import com.jeanbernad.randomuser.data.user.remote.UserRemoteDataSource
import com.jeanbernad.randomuser.domain.ErrorDomainMapper
import com.jeanbernad.randomuser.domain.user.*
import com.jeanbernad.randomuser.presentation.user.BaseUserDomainToPresentationMapper
import com.jeanbernad.randomuser.presentation.user.UserPresentationModel
import dagger.Binds
import dagger.Module

@Module
interface UserBindModule {
    @Binds
    fun bindUserRepository(userRepository: BaseUserRepository<UserDomain>): UserRepository<UserDomain>

    @Binds
    fun bindUserInteractor(userInteractor: UserInteractor.Base): UserInteractor

    @Binds
    fun bindUserDataToDomainMapper(userDataToDomainMapper: BaseUserDataToDomainMapper): UserDataToDomainMapper<UserDomain>

    @Binds
    fun bindUserRemoteDataSource(userRemoteDataSource: UserRemoteDataSource.Base): UserRemoteDataSource

    @Binds
    fun bindUserLocalDataSource(userLocalDataSource: UserLocalDataSource.Base): UserLocalDataSource

    @Binds
    fun bindUserDataToDbMapper(speciesDataToDbMapper: ToUserLocalMapper.Base): ToUserLocalMapper<UserLocalModel>

    @Binds
    fun bindToUserMapper(toSpeciesMapper: ToUserMapper.Base): ToUserMapper

    @Binds
    fun bindUserDomainToPresentationMapper(userDomainToPresentationMapper: BaseUserDomainToPresentationMapper): UserDomainToPresentationMapper<UserPresentationModel>

    @Binds
    fun bindErrorDomainMapper(errorDomainMapper: ErrorDomainMapper.Base): ErrorDomainMapper
}