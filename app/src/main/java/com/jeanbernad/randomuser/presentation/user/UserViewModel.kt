package com.jeanbernad.randomuser.presentation.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeanbernad.randomuser.domain.UserDomainToPresentationMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel constructor(
    private val interactor: UserInteractor,
    private val uiMapper: UserDomainToPresentationMapper<UserPresentationModel>,
    private val communication: UserCommunication
) : ViewModel() {

    fun getInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            val uiInfo = interactor.user().map(uiMapper)
            withContext(Dispatchers.Main) {
                communication.map(uiInfo)
            }
        }
    }
}