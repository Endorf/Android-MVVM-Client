package com.mvvm.sharednotes.login.data.exception

class EmailNotValidException : IllegalStateException(MESSAGE) {

    companion object {
        const val MESSAGE = "Email is not valid"
    }
}