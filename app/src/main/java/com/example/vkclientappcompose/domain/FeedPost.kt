package com.example.vkclientappcompose.domain

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.example.vkclientappcompose.R
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
data class FeedPost(
    val id: Int = 0,
    val communityName: String = "Group name",
    val publicationDate: String = "18:04",
    val avatarResId: Int = R.drawable.post_comunity_thumbnail,
    val contentText: String = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ",
    val contentImageResId: Int = R.drawable.post_content_image,
    var statistics: List<StatisticItem> = listOf(
        StatisticItem(StatisticType.VIEWS, 999),
        StatisticItem(StatisticType.SHARES, 9),
        StatisticItem(StatisticType.COMMENTS, 99),
        StatisticItem(StatisticType.LIKES, 100),
    )
) : Parcelable {
    companion object {

        val NavigationType: NavType<FeedPost> = object : NavType<FeedPost>(false) {
            override fun get(bundle: Bundle, key: String): FeedPost? {
                return bundle.getParcelable(key)
            }

            override fun parseValue(value: String): FeedPost {
                return Gson().fromJson(value, FeedPost::class.java)
            }

            override fun put(bundle: Bundle, key: String, value: FeedPost) {
                bundle.putParcelable(key, value)
            }

        }
    }
}