package com.mvvm.sharednotes.login.data

import com.mvvm.sharednotes.login.view.entity.User

interface UserRepository {

    suspend fun create(email: String): Result<User?>

    suspend fun read(email: String): Result<User?>
}