package com.mvvm.sharednotes.login.domain

import com.mvvm.sharednotes.login.view.entity.User

interface Interactor {

    suspend fun login(email: String): Result<User?>
}