package com.example.vkclientappcompose.domain

import com.example.vkclientappcompose.R

data class PostComment(
    val id: Int,
    val authorName: String = "Author",
    val authorAvatarId: Int = R.drawable.comment_author_avatar,
    val commentText: String = "Long Comment text",
    val publicationDate: String = "12:30"
)
