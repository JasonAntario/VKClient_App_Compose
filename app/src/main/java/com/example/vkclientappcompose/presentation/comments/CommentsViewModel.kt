package com.example.vkclientappcompose.presentation.comments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.vkclientappcompose.data.repository.NewsFeedRepositoryImpl
import com.example.vkclientappcompose.domain.entity.FeedPost
import com.example.vkclientappcompose.domain.usecases.GetCommentsUseCase
import kotlinx.coroutines.flow.map

class CommentsViewModel(
    feedPost: FeedPost,
    application: Application
) : AndroidViewModel(application) {

    private val repository = NewsFeedRepositoryImpl(getApplication())

    private val getCommentsUseCase = GetCommentsUseCase(repository)

    val screenState = getCommentsUseCase(feedPost)
        .map {
            CommentsScreenState.Comments(
                feedPost = feedPost,
                comments = it
            )
        }
}