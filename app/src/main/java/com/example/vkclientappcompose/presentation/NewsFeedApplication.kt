package com.example.vkclientappcompose.presentation

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.vkclientappcompose.di.ApplicationComponent
import com.example.vkclientappcompose.di.DaggerApplicationComponent

class NewsFeedApplication : Application() {

    val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}

@Composable
fun getApplicationComponent(): ApplicationComponent {
    return (LocalContext.current.applicationContext as NewsFeedApplication).component
}