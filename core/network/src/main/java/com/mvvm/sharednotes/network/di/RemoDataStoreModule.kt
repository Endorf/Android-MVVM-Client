package com.mvvm.sharednotes.network.di

import com.mvvm.sharednotes.network.data.storage.RemoteUserDataStore
import com.mvvm.sharednotes.network.data.storage.RemoteUserDataStoreImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(FragmentComponent::class, ViewModelComponent::class)
interface RemoDataStoreModule {

    @Binds
    fun provideUserApi(dataStore: RemoteUserDataStoreImpl): RemoteUserDataStore
}