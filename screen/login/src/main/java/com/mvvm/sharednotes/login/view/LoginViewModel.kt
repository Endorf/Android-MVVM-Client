package com.mvvm.sharednotes.login.view

import android.util.Patterns
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.mvvm.sharednotes.login.data.exception.EmailNotValidException
import com.mvvm.sharednotes.login.domain.Interactor
import com.mvvm.sharednotes.login.view.entity.User
import com.mvvm.sharednotes.login.view.entity.state.ErrorState
import com.mvvm.sharednotes.login.view.entity.state.LoginUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
@Suppress("UnusedPrivateMember")
class LoginViewModel @Inject constructor(
    private val interactor: Interactor,
    private val bundle: SavedStateHandle
) : ViewModel() {
    var userInput: String = ""

    val keyBoardVisibilityState: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }

    val errorState: LiveData<ErrorState>

    private val _inProgressTest: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val inProgressState: LiveData<Boolean>

    val loginState: LiveData<LoginUiState>

    private val sourceLiveData = MutableLiveData<Result<User?>>()

    init {
        with(MediatorLiveData<Boolean>()) {
            addSource(sourceLiveData) { value = false }
            addSource(_inProgressTest) { value = it }
            inProgressState = this
        }

        errorState = sourceLiveData.switchMap { result ->
            liveData {
                if (result.isFailure) {
                    result.exceptionOrNull()?.let { emit(ErrorState(it, it.message)) }
                }
            }
        }
        loginState = sourceLiveData.switchMap { result ->
            liveData {
                if (result.isFailure) {
                    LoginUiState(true, result.getOrNull())
                } else {
                    LoginUiState(false)
                }
            }
        }
    }

    fun login() {
        keyBoardVisibilityState.value = false
        val validationStatus = validateUserInput { error ->
            error?.let { sourceLiveData.value = Result.failure(it) }
        }

        if (validationStatus) viewModelScope.launch {
            _inProgressTest.value = true
            sourceLiveData.value = interactor.login(userInput)
        }
    }

    private inline fun validateUserInput(errorStateCallback: (Throwable?) -> Unit): Boolean =
        when {
            isEmailValid(userInput).not() -> {
                errorStateCallback(EmailNotValidException())
                false
            }
            else -> {
                errorStateCallback(null)
                true
            }
        }

    companion object {
        private fun isEmailValid(email: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}