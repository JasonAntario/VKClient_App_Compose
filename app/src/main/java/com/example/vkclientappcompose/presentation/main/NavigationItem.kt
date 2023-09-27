package com.example.vkclientappcompose.presentation.main

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.vkclientappcompose.R
import com.example.vkclientappcompose.navigation.Screen

sealed class NavigationItem(
    val screen: Screen,
    @StringRes val titleResId: Int,
    val icon: ImageVector
) {

    object Home : NavigationItem(
        Screen.Home,
        R.string.nav_item_home,
        Icons.Outlined.Home
    )

    object Favourite : NavigationItem(
        Screen.Favourites,
        R.string.nav_item_favourite,
        Icons.Outlined.Favorite
    )

    object Profile : NavigationItem(
        Screen.Profile,
        R.string.nav_item_profile,
        Icons.Outlined.Person
    )
}