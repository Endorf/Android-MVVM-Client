package com.mvvm.sharednotes.login.di

import com.mvvm.sharednotes.login.data.UserRepository
import com.mvvm.sharednotes.login.data.UserRepositoryImpl
import com.mvvm.sharednotes.login.domain.Interactor
import com.mvvm.sharednotes.login.domain.LoginInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface LoginModule {

    @Binds
    @ViewModelScoped
    fun bindLoginIteractor(interactor: LoginInteractor): Interactor

    @Binds
    @ViewModelScoped
    fun bindLoginRepository(repository: UserRepositoryImpl): UserRepository
}