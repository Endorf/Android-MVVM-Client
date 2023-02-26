package com.mvvm.sharednotes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RoutingActivityViewModel : ViewModel() {

    val isUserSignedIn: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    fun retrieveUser() {
        isUserSignedIn.value = false
    }
}