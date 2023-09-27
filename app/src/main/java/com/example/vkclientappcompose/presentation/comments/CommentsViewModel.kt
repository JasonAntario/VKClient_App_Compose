package com.example.vkclientappcompose.presentation.comments

import androidx.lifecycle.ViewModel
import com.example.vkclientappcompose.domain.entity.FeedPost
import com.example.vkclientappcompose.domain.usecases.GetCommentsUseCase
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CommentsViewModel @Inject constructor(
    private val feedPost: FeedPost,
    getCommentsUseCase: GetCommentsUseCase
) : ViewModel() {

    val screenState = getCommentsUseCase(feedPost)
        .map {
            CommentsScreenState.Comments(
                feedPost = feedPost,
                comments = it
            )
        }
}