package com.mvvm.sharednotes.storage.realm

import com.mvvm.sharednotes.storage.BuildConfig
import com.mvvm.sharednotes.storage.realm.entity.db.AddressEntity
import com.mvvm.sharednotes.storage.realm.entity.db.PostEntity
import com.mvvm.sharednotes.storage.realm.entity.db.UserEntity
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

interface RealmDB {

    companion object {

        fun beginTransaction(): Realm = realm!!

        fun initializeRealm() = RealmConfiguration.Builder(
            setOf(UserEntity::class, AddressEntity::class, PostEntity::class)
        )
            .name(BuildConfig.DB_NAME)
            .schemaVersion(BuildConfig.DB_VERSION)
            .deleteRealmIfMigrationNeeded()
            .build()
            .also { config ->
                realm = Realm.open(config)
            }

        private var realm: Realm? = null
    }
}