package com.mvvm.sharednotes.login.domain

import com.mvvm.sharednotes.login.data.UserRepository
import com.mvvm.sharednotes.login.view.entity.User
import javax.inject.Inject

class LoginInteractor @Inject constructor(
    private val repository: UserRepository
) : Interactor {

    override suspend fun login(email: String): Result<User?> {
        return repository.read(email)
            .takeIf { it.isSuccess }
            ?: repository.create(email)
    }
}