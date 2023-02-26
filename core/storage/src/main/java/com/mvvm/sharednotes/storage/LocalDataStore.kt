package com.mvvm.sharednotes.storage

interface LocalDataStore<T> {

    suspend fun create(value: T)

    suspend fun read(value: T): Result<T?>
}