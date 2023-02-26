package com.mvvm.sharednotes.storage.realm

import com.mvvm.sharednotes.storage.realm.entity.User
import com.mvvm.sharednotes.storage.realm.entity.db.UserEntity
import com.mvvm.sharednotes.storage.realm.entity.mapper.asResult
import com.mvvm.sharednotes.storage.realm.entity.mapper.toUser
import com.mvvm.sharednotes.storage.realm.entity.mapper.toUserEntity

class RealmUserDataStoreImpl : LocalUserDataStore {

    override suspend fun create(value: User) {
        RealmDB.beginTransaction().write {
            copyToRealm(value.toUserEntity())
        }.toUser()
    }

    override suspend fun read(value: User): Result<User?> =
        RealmDB.beginTransaction().query(
            UserEntity::class, "email == $0", "${value.email}"
        ).first().find()?.toUser().asResult()
}