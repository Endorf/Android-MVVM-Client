package com.mvvm.sharednotes.storage.realm.entity.db

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.BsonObjectId
import org.mongodb.kbson.ObjectId

class PostEntity : RealmObject {

    constructor()

    constructor(message: String? = null) {
        this.message = message
    }

    @PrimaryKey
    var id: ObjectId = BsonObjectId()

    var message: String? = null
}