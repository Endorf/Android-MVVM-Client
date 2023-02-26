package com.mvvm.sharednotes.login.view.entity.state

data class ErrorState(
    val error: Throwable,
    val message: String?
) {
    companion object {
        fun create(error: Throwable?) = error?.let { ErrorState(error, error.message) }
    }
}
