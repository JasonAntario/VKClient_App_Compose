package com.example.vkclientappcompose.presentation.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthenticationResult

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val _authState = MutableLiveData<AuthState>(AuthState.Initial)
    val authState: LiveData<AuthState> = _authState

    init {
        val storage = VKPreferencesKeyValueStorage(application)
        val token = VKAccessToken.restore(storage)
        Log.i(TAG, "TOKEN - ${token?.accessToken}")
        val isLoggedIn = token != null && token.isValid
        _authState.value = if (isLoggedIn) AuthState.Authorized else AuthState.NotAuthorized
    }

    fun performAuthResult(result: VKAuthenticationResult) {
        when (result) {
            is VKAuthenticationResult.Failed -> {
                _authState.value = AuthState.NotAuthorized
            }

            is VKAuthenticationResult.Success -> {
                _authState.value = AuthState.Authorized
            }
        }
    }

    companion object{
        private const val TAG = "MainViewModel"
    }
}