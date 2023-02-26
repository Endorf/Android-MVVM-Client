package com.mvvm.sharednotes.network.data.api.user

import com.mvvm.sharednotes.network.data.api.user.entity.UserEntity
import retrofit2.http.*

interface UserApi {

    @GET("users/{id}")
    suspend fun get(@Path("id") id: Int): UserEntity

    @POST("user")
    @FormUrlEncoded
    suspend fun create(@Body user: UserEntity): UserEntity
}