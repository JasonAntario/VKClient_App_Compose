package com.example.vkclientappcompose.domain.usecases

import com.example.vkclientappcompose.domain.entity.FeedPost
import com.example.vkclientappcompose.domain.repository.NewsFeedRepository

class DeletePostUseCase(
    private val repository: NewsFeedRepository
) {
    suspend operator fun invoke(feedPost: FeedPost) {
        return repository.deletePost(feedPost)
    }
}