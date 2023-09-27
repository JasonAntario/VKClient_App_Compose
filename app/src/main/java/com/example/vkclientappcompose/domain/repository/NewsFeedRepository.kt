package com.example.vkclientappcompose.domain.repository

import com.example.vkclientappcompose.domain.entity.AuthState
import com.example.vkclientappcompose.domain.entity.FeedPost
import com.example.vkclientappcompose.domain.entity.PostComment
import kotlinx.coroutines.flow.StateFlow

interface NewsFeedRepository {

    fun getAuthStateFlow(): StateFlow<AuthState>
    fun getRecommendations(): StateFlow<List<FeedPost>>
    fun getComments(feedPost: FeedPost): StateFlow<List<PostComment>>
    suspend fun deletePost(feedPost: FeedPost)
    suspend fun changeLikeStatus(feedPost: FeedPost)
    suspend fun loadNextData()
    suspend fun checkAuthState()
}