package com.mvvm.sharednotes.storage.di

import com.mvvm.sharednotes.storage.realm.LocalUserDataStore
import com.mvvm.sharednotes.storage.realm.RealmUserDataStoreImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(FragmentComponent::class, ViewModelComponent::class)
interface LocalDataStoraModule {

    @Binds
    fun bindLocalDataStore(store: RealmUserDataStoreImpl): LocalUserDataStore
}