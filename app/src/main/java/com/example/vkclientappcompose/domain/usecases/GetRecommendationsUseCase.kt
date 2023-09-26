package com.example.vkclientappcompose.domain.usecases

import com.example.vkclientappcompose.domain.entity.FeedPost
import com.example.vkclientappcompose.domain.repository.NewsFeedRepository
import kotlinx.coroutines.flow.StateFlow

class GetRecommendationsUseCase(
    private val repository: NewsFeedRepository
) {
    operator fun invoke(): StateFlow<List<FeedPost>> {
        return repository.getRecommendations()
    }
}