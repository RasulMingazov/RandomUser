package com.jeanbernad.randomuser.presentation.user

import androidx.lifecycle.*
import com.jeanbernad.randomuser.domain.user.UserDomainToPresentationMapper
import com.jeanbernad.randomuser.domain.user.UserInteractor
import kotlinx.coroutines.*

class UserViewModel(
    private val interactor: UserInteractor,
    private val presentationMapper: UserDomainToPresentationMapper<UserPresentationModel>,
) : ViewModel() {

    private var reloadTrigger = MutableLiveData<Boolean>()

    init {
        refresh()
    }

    val user = Transformations.switchMap(reloadTrigger) {
        liveData(Dispatchers.IO) {
            emit(UserPresentationModel.Progress)
            val presentationUser = interactor.user().map(presentationMapper)
            withContext(Dispatchers.IO) {
                emit(presentationUser)
            }
        }
    }

    fun refresh() {
        reloadTrigger.value = true
    }
}
