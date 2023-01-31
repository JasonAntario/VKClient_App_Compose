package com.example.vkclientappcompose.domain

import com.example.vkclientappcompose.R

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
)