package com.example.vkclientappcompose.domain.usecases

import com.example.vkclientappcompose.domain.entity.FeedPost
import com.example.vkclientappcompose.domain.repository.NewsFeedRepository
import javax.inject.Inject

class ChangeLikeStatusUseCase @Inject constructor(
    private val repository: NewsFeedRepository
) {
    suspend operator fun invoke(feedPost: FeedPost) {
        return repository.changeLikeStatus(feedPost)
    }
}