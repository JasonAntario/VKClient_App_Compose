package com.example.vkclientappcompose.domain.usecases

import com.example.vkclientappcompose.domain.repository.NewsFeedRepository
import javax.inject.Inject

class LoadNextDataUseCase @Inject constructor(
    private val repository: NewsFeedRepository
) {
    suspend operator fun invoke() {
        return repository.loadNextData()
    }
}