package com.example.vkclientappcompose.presentation.comments

import com.example.vkclientappcompose.domain.entity.FeedPost
import com.example.vkclientappcompose.domain.entity.PostComment

sealed class CommentsScreenState {

    object Initial : CommentsScreenState()

    data class Comments(
        val feedPost: FeedPost,
        val comments: List<PostComment>
    ) : CommentsScreenState()
}