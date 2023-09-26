package com.example.vkclientappcompose.domain.usecases

import com.example.vkclientappcompose.domain.entity.AuthState
import com.example.vkclientappcompose.domain.repository.NewsFeedRepository
import kotlinx.coroutines.flow.StateFlow

class GetAuthStateFlowUseCase(
    private val repository: NewsFeedRepository
) {
    operator fun invoke(): StateFlow<AuthState> {
        return repository.getAuthStateFlow()
    }
}