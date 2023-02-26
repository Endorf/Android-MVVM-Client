package com.mvvm.sharednotes.storage.realm.entity.db

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.Ignore
import io.realm.kotlin.types.annotations.Index
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

internal class UserEntity : RealmObject {

    constructor()

    constructor(
        email: String? = null,
        name: String? = null,
        userName: String? = null,
        address: AddressEntity? = null
    ) : this() {
        this.email = email
        this.name = name
        this.userName = userName
        this.address = address
    }

    @PrimaryKey
    var id: ObjectId = ObjectId()

    @Index
    var email: String? = null

    var name: String? = null

    var userName: String? = null

    var address: AddressEntity? = null

    var addresses: RealmList<PostEntity> = realmListOf()

    @Ignore
    var tempId: Int = 0
}
