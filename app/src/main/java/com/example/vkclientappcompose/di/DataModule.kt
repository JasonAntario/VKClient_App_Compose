package com.example.vkclientappcompose.di

import android.content.Context
import com.example.vkclientappcompose.data.network.ApiFactory
import com.example.vkclientappcompose.data.network.ApiService
import com.example.vkclientappcompose.data.repository.NewsFeedRepositoryImpl
import com.example.vkclientappcompose.domain.repository.NewsFeedRepository
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindRepository(impl: NewsFeedRepositoryImpl): NewsFeedRepository

    companion object {
        @ApplicationScope
        @Provides
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }

        @ApplicationScope
        @Provides
        fun provideVkStorage(
            context: Context
        ): VKPreferencesKeyValueStorage {
            return VKPreferencesKeyValueStorage(context)
        }
    }
}