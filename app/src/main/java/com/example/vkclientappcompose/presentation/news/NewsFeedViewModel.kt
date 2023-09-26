package com.example.vkclientappcompose.presentation.news

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.vkclientappcompose.data.repository.NewsFeedRepository
import com.example.vkclientappcompose.domain.FeedPost
import com.example.vkclientappcompose.extensions.mergeWith
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class NewsFeedViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = NewsFeedRepository(getApplication())

    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.e("NewsFeedViewModel", "Exception caught by exception handler.")
    }

    private val recommendationFlow = repository.recommendations

    private val loadNextDataEvents = MutableSharedFlow<Unit>()
    private val loadNextDataFlow = flow {
        loadNextDataEvents.collect {
            emit(
                NewsFeedScreenState.Posts(
                    posts = recommendationFlow.value,
                    nextDataIsLoading = true
                )
            )
        }
    }

    val screenState = recommendationFlow
        .filter { it.isNotEmpty() }
        .map { NewsFeedScreenState.Posts(it) as NewsFeedScreenState }
        .onStart { NewsFeedScreenState.Loading }
        .mergeWith(loadNextDataFlow)

    fun loadNextRecommendations() {
        viewModelScope.launch {
            loadNextDataEvents.emit(Unit)
            repository.loadNextData()
        }
    }

    fun changeLikeStatus(feedPost: FeedPost) {
        viewModelScope.launch(exceptionHandler) {
            repository.changeLikeStatus(feedPost)
        }
    }

    fun remove(feedPost: FeedPost) {
        viewModelScope.launch(exceptionHandler) {
            repository.deletePost(feedPost)
        }
    }
}