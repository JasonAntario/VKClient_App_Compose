package com.example.vkclientappcompose.presentation.news

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vkclientappcompose.domain.entity.FeedPost
import com.example.vkclientappcompose.domain.usecases.ChangeLikeStatusUseCase
import com.example.vkclientappcompose.domain.usecases.DeletePostUseCase
import com.example.vkclientappcompose.domain.usecases.GetRecommendationsUseCase
import com.example.vkclientappcompose.domain.usecases.LoadNextDataUseCase
import com.example.vkclientappcompose.extensions.mergeWith
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsFeedViewModel @Inject constructor(

    getRecommendationsUseCase: GetRecommendationsUseCase,
    private val loadNextDataUseCase: LoadNextDataUseCase,
    private val changeLikeStatusUseCase: ChangeLikeStatusUseCase,
    private val deletePostUseCase: DeletePostUseCase
) : ViewModel() {


    private val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.e("NewsFeedViewModel", "Exception caught by exception handler.")
    }


    private val recommendationFlow = getRecommendationsUseCase()

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
            loadNextDataUseCase()
        }
    }

    fun changeLikeStatus(feedPost: FeedPost) {
        viewModelScope.launch(exceptionHandler) {
            changeLikeStatusUseCase(feedPost)
        }
    }

    fun remove(feedPost: FeedPost) {
        viewModelScope.launch(exceptionHandler) {
            deletePostUseCase(feedPost)
        }
    }
}