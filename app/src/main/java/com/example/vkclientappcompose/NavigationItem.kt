package com.example.vkclientappcompose

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(
    @StringRes val titleResId: Int,
    val icon: ImageVector
) {

    object Home : NavigationItem(
        R.string.nav_item_home,
        Icons.Outlined.Home
    )

    object Favourite : NavigationItem(
        R.string.nav_item_favourite,
        Icons.Outlined.Favorite
    )

    object Profile : NavigationItem(
        R.string.nav_item_profile,
        Icons.Outlined.Person
    )
}