package com.example.vkclientappcompose.presentation.news

import com.example.vkclientappcompose.domain.FeedPost

sealed class NewsFeedScreenState {

    object Initial : NewsFeedScreenState()

    data class Posts(val posts: List<FeedPost>) : NewsFeedScreenState()
}