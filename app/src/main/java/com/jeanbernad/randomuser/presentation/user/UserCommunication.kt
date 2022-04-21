package com.jeanbernad.randomuser.presentation.user

import com.jeanbernad.randomuser.core.Communication

interface UserCommunication : Communication<UserPresentationModel> {
    class Base : Communication.Base<UserPresentationModel>(), UserCommunication
}