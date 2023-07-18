package com.example.vkclientappcompose.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vkclientappcompose.domain.FeedPost
import com.example.vkclientappcompose.domain.PostComment
import com.example.vkclientappcompose.presentation.CommentsScreenState

class CommentsViewModel(
    feedPost: FeedPost
) : ViewModel() {

    private val _screenState = MutableLiveData<CommentsScreenState>(CommentsScreenState.Initial)
    val screenState: LiveData<CommentsScreenState> = _screenState

    init {
        loadComments(feedPost)
    }

    private fun loadComments(feedPost: FeedPost) {
        val comments = mutableListOf<PostComment>().apply {
            repeat(10) {
                add(PostComment(id = it))
            }
        }
        _screenState.value = CommentsScreenState.Comments(feedPost, comments)
    }
}