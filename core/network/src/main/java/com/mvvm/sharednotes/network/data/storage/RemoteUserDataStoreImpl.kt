package com.mvvm.sharednotes.network.data.storage

import com.mvvm.sharednotes.network.BuildConfig
import com.mvvm.sharednotes.network.data.api.ApiFactory
import com.mvvm.sharednotes.network.data.api.user.UserApi
import com.mvvm.sharednotes.network.data.api.user.entity.UserEntity

class RemoteUserDataStoreImpl : RemoteUserDataStore {

    private val api: UserApi = ApiFactory(UserApi::class.java).create(BuildConfig.BASE_URL)

    override suspend fun create(value: String?): Result<UserEntity> =
        api.runCatching { create(UserEntity(email = value)) }

    override suspend fun read(value: String?): Result<UserEntity> =
        api.runCatching { get(91) }
}