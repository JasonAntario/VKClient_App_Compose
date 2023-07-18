package com.example.vkclientappcompose.presentation

import com.example.vkclientappcompose.domain.FeedPost
import com.example.vkclientappcompose.domain.PostComment

sealed class CommentsScreenState {

    object Initial : CommentsScreenState()

    data class Comments(
        val feedPost: FeedPost,
        val comments: List<PostComment>
    ) : CommentsScreenState()
}