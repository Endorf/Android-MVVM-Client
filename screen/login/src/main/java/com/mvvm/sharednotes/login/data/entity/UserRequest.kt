package com.mvvm.sharednotes.login.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserRequest(
    val id: Int,
    var email: String?
) : Parcelable
