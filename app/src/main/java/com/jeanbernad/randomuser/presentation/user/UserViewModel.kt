package com.jeanbernad.randomuser.presentation.user

import androidx.lifecycle.*
import com.jeanbernad.randomuser.domain.user.UserDomainToPresentationMapper
import com.jeanbernad.randomuser.domain.user.UserInteractor
import com.jeanbernad.randomuser.domain.user.all.UsersDomainToPresentationMapper
import com.jeanbernad.randomuser.presentation.user.all.UsersPresentationModel
import kotlinx.coroutines.*
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val interactor: UserInteractor,
    private val presentationMapper: UserDomainToPresentationMapper<UserPresentationModel>,
    private val usersPresentationMapper: UsersDomainToPresentationMapper<UsersPresentationModel>
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

    val users = Transformations.switchMap(reloadTrigger) {
        liveData(Dispatchers.IO) {
            val prUsers = interactor.users().map(usersPresentationMapper)
            withContext(Dispatchers.IO) {
                emit(prUsers)
            }
        }
    }

    fun refresh() {
        reloadTrigger.value = true
    }
}
