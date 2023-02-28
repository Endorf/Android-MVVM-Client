package com.mvvm.sharednotes.login.view.entity.state

import com.mvvm.sharednotes.login.view.entity.User

data class LoginUiState(
    val isSuccessfullyLogined: Boolean = false,
    val user: User? = null
)
