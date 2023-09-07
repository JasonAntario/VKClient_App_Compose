package com.example.vkclientappcompose.data.repository

import android.app.Application
import com.example.vkclientappcompose.data.mapper.NewsFeedMapper
import com.example.vkclientappcompose.data.network.ApiFactory
import com.example.vkclientappcompose.domain.FeedPost
import com.example.vkclientappcompose.domain.PostComment
import com.example.vkclientappcompose.domain.StatisticItem
import com.example.vkclientappcompose.domain.StatisticType
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import com.vk.api.sdk.auth.VKAccessToken

class NewsFeedRepository(application: Application) {

    private val storage = VKPreferencesKeyValueStorage(application)
    private val token = VKAccessToken.restore(storage)

    private val apiService = ApiFactory.apiService
    private val mapper = NewsFeedMapper()

    private var nextFrom: String? = null

    private val _feedPosts = mutableListOf<FeedPost>()
    val feedPosts: List<FeedPost>
        get() = _feedPosts.toList()

    suspend fun loadRecommendations(): List<FeedPost> {
        val startFrom = nextFrom
        if (startFrom == null && _feedPosts.isNotEmpty()) {
            return feedPosts
        }

        val response =
            if (startFrom == null) {
                apiService.loadRecommendations(getAccessToken())
            } else {
                apiService.loadRecommendations(
                    getAccessToken(),
                    startFrom
                )
            }
        nextFrom = response.newsFeedContent.nextFrom
        val posts = mapper.mapResponseToPosts(response)
        _feedPosts.addAll(posts)
        return feedPosts
    }

    private fun getAccessToken(): String {
        return token?.accessToken ?: throw IllegalStateException("token is null")
    }

    suspend fun deletePost(feedPost: FeedPost) {
        apiService.ignorePost(
            getAccessToken(),
            feedPost.communityId,
            feedPost.id
        )
        _feedPosts.remove(feedPost)
    }

    suspend fun changeLikeStatus(feedPost: FeedPost) {
        val response = if (feedPost.isLiked) {
            apiService.deleteLike(
                token = getAccessToken(),
                ownerId = feedPost.communityId,
                postId = feedPost.id
            )
        } else {
            apiService.addLike(
                token = getAccessToken(),
                ownerId = feedPost.communityId,
                postId = feedPost.id
            )
        }
        val newLikesCount = response.likes.count
        val newStatistics = feedPost.statistics.toMutableList().apply {
            removeIf { it.type == StatisticType.LIKES }
            add(StatisticItem(type = StatisticType.LIKES, newLikesCount))
        }
        val newPost = feedPost.copy(statistics = newStatistics, isLiked = !feedPost.isLiked)
        val postIndex = _feedPosts.indexOf(feedPost)
        _feedPosts[postIndex] = newPost
    }

    suspend fun getComments(feedPost: FeedPost): List<PostComment> {
        val response = apiService.getComments(
            getAccessToken(),
            feedPost.communityId,
            feedPost.id
        )
        return mapper.mapResponseToComments(response)
    }
}