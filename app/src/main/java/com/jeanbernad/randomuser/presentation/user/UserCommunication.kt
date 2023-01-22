package com.jeanbernad.randomuser.presentation.user

import com.jeanbernad.randomuser.core.Communication
import javax.inject.Inject

interface UserCommunication : Communication<UserPresentationModel> {
    class Base @Inject constructor() : Communication.Base<UserPresentationModel>(), UserCommunication
}