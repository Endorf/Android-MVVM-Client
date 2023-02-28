package com.mvvm.sharednotes.login.view

import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvvm.sharednotes.login.data.exception.EmailNotValidException
import com.mvvm.sharednotes.login.domain.Interactor
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

    val inProgressState: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val errorState: MutableLiveData<ErrorState> by lazy { MutableLiveData<ErrorState>() }

    val loginState: MutableLiveData<LoginUiState> by lazy { MutableLiveData<LoginUiState>() }

    fun login() {
        keyBoardVisibilityState.value = false
        val validationStatus = validateUserInput {
            errorState.value = ErrorState.create(it)
        }

        if (validationStatus) viewModelScope.launch {

            inProgressState.value = true
            val result = interactor.login(userInput)
            when {
                result.isSuccess -> loginState.value = LoginUiState(true, result.getOrNull())
                result.isFailure -> {
                    loginState.value = LoginUiState(false)
                    errorState.value =
                        result.exceptionOrNull()?.let { ErrorState(it, it.message) }
                }
            }
            inProgressState.value = false
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