package com.example.vkclientappcompose.domain.usecases

import com.example.vkclientappcompose.domain.repository.NewsFeedRepository

class LoadNextDataUseCase(
    private val repository: NewsFeedRepository
) {
    suspend operator fun invoke() {
        return repository.loadNextData()
    }
}