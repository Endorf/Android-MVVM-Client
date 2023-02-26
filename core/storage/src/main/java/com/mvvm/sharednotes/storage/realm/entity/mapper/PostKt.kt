package com.mvvm.sharednotes.storage.realm.entity.mapper

import com.mvvm.sharednotes.storage.realm.entity.Post
import com.mvvm.sharednotes.storage.realm.entity.db.PostEntity

internal fun Post.toUserEntity() = PostEntity(message)

internal fun PostEntity.toUser() = Post(message)