package com.mvvm.sharednotes.storage.di

import com.mvvm.sharednotes.storage.BuildConfig
import com.mvvm.sharednotes.storage.realm.entity.db.AddressEntity
import com.mvvm.sharednotes.storage.realm.entity.db.PostEntity
import com.mvvm.sharednotes.storage.realm.entity.db.UserEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ReamlModule {

    @Provides
    fun provideRealmInstance(config: RealmConfiguration) = Realm.open(config)

    @Singleton
    @Provides
    fun provideRealmConfig() = RealmConfiguration.Builder(
        setOf(
            UserEntity::class, AddressEntity::class,
            PostEntity::class
        )
    )
        .name(BuildConfig.DB_NAME)
        .schemaVersion(BuildConfig.DB_VERSION)
        .build()
}