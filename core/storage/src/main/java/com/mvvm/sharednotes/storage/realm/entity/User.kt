package com.mvvm.sharednotes.storage.realm.entity

data class User(
    val email: String? = null,
    val name: String? = null,
    val userName: String? = null,
    val address: Address? = null
)
