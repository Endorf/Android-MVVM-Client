package com.mvvm.sharednotes.login.data

import com.mvvm.sharednotes.login.view.entity.User
import com.mvvm.sharednotes.network.data.storage.RemoteUserDataStore
import com.mvvm.sharednotes.storage.realm.LocalUserDataStore
import javax.inject.Inject

typealias NetworkUser = com.mvvm.sharednotes.network.data.api.user.entity.UserEntity
typealias DataStoreUser = com.mvvm.sharednotes.storage.realm.entity.User

class UserRepositoryImpl @Inject constructor(
    private val localDataStore: LocalUserDataStore,
    private val remoteDataStore: RemoteUserDataStore
) : UserRepository {

    override suspend fun create(email: String): Result<User?> {
        val remoteResult = remoteDataStore.create(email).map {
            User(it.email, it.name, it.userName)
        }
        remoteResult.getOrNull()?.let {
            localDataStore.create(
                DataStoreUser(it.email, it.name, it.userName)
            )
        }
        return remoteResult
    }

    override suspend fun read(email: String): Result<User?> {
        return localDataStore.read(DataStoreUser(email))
            .takeIf { it.isSuccess }
            ?.map { User(it!!.email, it.name, it.userName) }
            ?: remoteDataStore.read(MOCK_ID)
                .map { User(it.email, it.name, it.userName) }
    }

    companion object {
        // TODO remove mock
        private const val MOCK_ID = 1
    }
}