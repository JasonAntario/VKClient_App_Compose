package com.example.vkclientappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.vkclientappcompose.presentation.MainViewModel
import com.example.vkclientappcompose.ui.theme.VKClientAppComposeTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VKClientAppComposeTheme {
                MainScreen(viewModel)
            }
        }
    }
}