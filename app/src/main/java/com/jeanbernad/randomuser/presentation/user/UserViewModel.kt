package com.jeanbernad.randomuser.presentation.user

import androidx.lifecycle.*
import com.jeanbernad.randomuser.domain.user.UserDomainToPresentationMapper
import com.jeanbernad.randomuser.domain.user.UserInteractor
import com.jeanbernad.randomuser.presentation.common.BaseViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

interface UserViewModel {

    fun user()

    fun shareValues(): String

    class Base @Inject constructor(
        private val interactor: UserInteractor,
        private val presentationMapper: UserDomainToPresentationMapper<UserPresentationModel>,
        private val communication: UserCommunication
    ) : BaseViewModel.Base<UserPresentationModel>(communication), UserViewModel {

        private var shareValues = ""


        override fun user() {
            communication.map(UserPresentationModel.Progress)
            viewModelScope.launch(Dispatchers.IO) {
                val user = interactor.user().map(presentationMapper)
                user.map(object : ToUserValueMapper {
                    override fun map(allValues: String) {
                        shareValues = allValues
                    }
                })
                withContext(Dispatchers.Main) {
                    communication.map(user)
                }
            }
        }

        override fun shareValues() = shareValues
    }
}
