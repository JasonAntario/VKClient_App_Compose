package com.example.vkclientappcompose.di

import com.example.vkclientappcompose.domain.entity.FeedPost
import com.example.vkclientappcompose.presentation.ViewModelFactory
import dagger.BindsInstance
import dagger.Subcomponent


@Subcomponent(
    modules = [
        CommentsViewModelModule::class
    ]
)
interface CommentsScreenComponent {

    fun getViewModelFactory(): ViewModelFactory

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance feedPost: FeedPost): CommentsScreenComponent
    }
}