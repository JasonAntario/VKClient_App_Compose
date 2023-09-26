package com.example.vkclientappcompose.presentation.comments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.vkclientappcompose.data.repository.NewsFeedRepository
import com.example.vkclientappcompose.domain.FeedPost
import kotlinx.coroutines.flow.map

class CommentsViewModel(
    feedPost: FeedPost,
    application: Application
) : AndroidViewModel(application) {

    private val repository = NewsFeedRepository(getApplication())

    val screenState = repository.getComments(feedPost)
        .map {
            CommentsScreenState.Comments(
                feedPost = feedPost,
                comments = it
            )
        }
}