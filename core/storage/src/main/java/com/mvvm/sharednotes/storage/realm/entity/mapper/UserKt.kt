package com.mvvm.sharednotes.storage.realm.entity.mapper

import com.mvvm.sharednotes.storage.exception.UserNotFoundException
import com.mvvm.sharednotes.storage.realm.entity.Address
import com.mvvm.sharednotes.storage.realm.entity.User
import com.mvvm.sharednotes.storage.realm.entity.db.AddressEntity
import com.mvvm.sharednotes.storage.realm.entity.db.UserEntity

internal fun User?.asResult() =
    this?.run { Result.success(this) }
        ?: Result.failure(UserNotFoundException())

internal fun User.toUserEntity() = UserEntity(
    email,
    name,
    userName,
    AddressEntity(address?.city)
)

internal fun UserEntity.toUser() = User(
    email,
    name,
    userName,
    Address(address?.city)
)