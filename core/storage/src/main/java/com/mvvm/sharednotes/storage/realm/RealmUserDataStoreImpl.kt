package com.mvvm.sharednotes.storage.realm

import com.mvvm.sharednotes.storage.realm.entity.User
import com.mvvm.sharednotes.storage.realm.entity.db.UserEntity
import com.mvvm.sharednotes.storage.realm.entity.mapper.asResult
import com.mvvm.sharednotes.storage.realm.entity.mapper.toUser
import com.mvvm.sharednotes.storage.realm.entity.mapper.toUserEntity
import io.realm.kotlin.Realm
import javax.inject.Inject

class RealmUserDataStoreImpl @Inject constructor(
    private val realm: Realm
) : LocalUserDataStore {

    override suspend fun create(value: User) {
        realm.write {
            copyToRealm(value.toUserEntity())
        }.toUser()
    }

    override suspend fun read(value: User): Result<User?> = realm
        .query(
            UserEntity::class,
            "email == $0",
            "${value.email}"
        ).first().find()?.toUser().asResult()
}
