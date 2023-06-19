package com.mvvm.sharednotes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RoutingActivityViewModel : ViewModel() {

    val isUserSignedIn: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }
    val keepScreenOnCondition: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    fun retrieveUser() {
        keepScreenOnCondition.value = false
        isUserSignedIn.value = false
    }
}