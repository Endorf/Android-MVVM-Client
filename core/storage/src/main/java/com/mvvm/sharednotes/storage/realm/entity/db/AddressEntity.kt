package com.mvvm.sharednotes.storage.realm.entity.db

import io.realm.kotlin.types.EmbeddedRealmObject

class AddressEntity : EmbeddedRealmObject {

    constructor()

    constructor(city: String?) : this() {
        this.city = city
    }

    var city: String? = null
}