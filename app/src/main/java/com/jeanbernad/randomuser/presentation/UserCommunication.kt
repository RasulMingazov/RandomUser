package com.jeanbernad.randomuser.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.jeanbernad.randomuser.core.Communication

interface UserCommunication : Communication<List<UserPresentationModel>> {
    class Base : UserCommunication {
        private val listLiveData = MutableLiveData<List<UserPresentationModel>>()
        private val listPokemon = mutableListOf<UserPresentationModel>()

        override fun map(data: List<UserPresentationModel>) {
            if (data.firstOrNull() is UserPresentationModel.Base) {
                listPokemon.addAll(data)
                listLiveData.value = listPokemon
            } else {
                listLiveData.value = data
            }
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<List<UserPresentationModel>>) {
            listLiveData.observe(owner, observer)
        }
    }
}