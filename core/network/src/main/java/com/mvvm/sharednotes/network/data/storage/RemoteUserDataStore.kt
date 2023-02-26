package com.mvvm.sharednotes.network.data.storage

import com.mvvm.sharednotes.network.data.api.user.entity.UserEntity

interface RemoteUserDataStore {

    suspend fun create(value: String?): Result<UserEntity>

    suspend fun read(id: Int): Result<UserEntity>
}