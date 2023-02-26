package com.mvvm.sharednotes.storage.exception

class UserNotFoundException : NullPointerException(MESSAGE) {

    companion object {
        private const val MESSAGE = "User not found"
    }
}