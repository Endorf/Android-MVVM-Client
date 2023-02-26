package com.mvvm.sharednotes.network.data.storage

import com.mvvm.sharednotes.network.data.api.user.UserApi
import com.mvvm.sharednotes.network.data.api.user.entity.UserEntity
import javax.inject.Inject

class RemoteUserDataStoreImpl @Inject constructor(
    private val api: UserApi
) : RemoteUserDataStore {

    override suspend fun create(value: String?): Result<UserEntity> =
        api.runCatching { create(UserEntity(email = value)) }

    override suspend fun read(id: Int): Result<UserEntity> =
        api.runCatching { get(id) }
}