package com.jeanbernad.randomuser.presentation

import com.jeanbernad.randomuser.domain.UserDomainToPresentationMapper

class BaseUserDomainToPresentationMapper : UserDomainToPresentationMapper<UserPresentationModel> {
    override fun map(id: String) = UserPresentationModel.Base(id)
}
