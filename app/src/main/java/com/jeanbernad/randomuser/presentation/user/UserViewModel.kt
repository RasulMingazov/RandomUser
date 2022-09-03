package com.jeanbernad.randomuser.presentation.user

import androidx.lifecycle.*
import com.jeanbernad.randomuser.domain.user.UserDomainToPresentationMapper
import com.jeanbernad.randomuser.domain.user.UserInteractor
import com.jeanbernad.randomuser.presentation.common.BaseViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

interface UserViewModel {
    fun user()

    class Base @Inject constructor(
        private val interactor: UserInteractor,
        private val presentationMapper: UserDomainToPresentationMapper<UserPresentationModel>,
        private val communication: UserCommunication
    ) : BaseViewModel.Base<UserPresentationModel>(communication), UserViewModel {

        override fun user() {
            viewModelScope.launch(Dispatchers.IO) {
                withContext(Dispatchers.Main) {
                    communication.map(UserPresentationModel.Progress)
                }
                val user = interactor.user().map(presentationMapper)
                withContext(Dispatchers.Main) {
                    communication.map(user)
                }
            }
        }
    }
}
