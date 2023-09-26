package com.example.vkclientappcompose.data.mapper

import android.util.Log
import com.example.vkclientappcompose.data.model.CommentsResponseDto
import com.example.vkclientappcompose.data.model.NewsFeedResponseDto
import com.example.vkclientappcompose.domain.entity.FeedPost
import com.example.vkclientappcompose.domain.entity.PostComment
import com.example.vkclientappcompose.domain.entity.StatisticItem
import com.example.vkclientappcompose.domain.entity.StatisticType
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import kotlin.math.absoluteValue

class NewsFeedMapper @Inject constructor() {


    fun mapResponseToPosts(responseDto: NewsFeedResponseDto): List<FeedPost> {
        val result = mutableListOf<FeedPost>()
        val posts = responseDto.newsFeedContent.posts
        val groups = responseDto.newsFeedContent.groups
        for (post in posts) {
            Log.e("TAG", "mapResponseToPosts: $post")
            val group = groups.find { it.id == post.communityId.absoluteValue } ?: break
            val feedPost = FeedPost(
                id = post.id,
                communityId = post.communityId,
                communityName = group.name,
                publicationDate = mapTimestampToDate(post.date),
                communityImageUrl = group.imageUrl,
                contentText = post.text,
                contentImageUrl = post.attachments?.firstOrNull()?.photo?.photoUrls?.last()?.url,
                statistics = listOf(
                    StatisticItem(StatisticType.COMMENTS, post.comments.count),
                    StatisticItem(StatisticType.LIKES, post.likes.count),
                    StatisticItem(StatisticType.SHARES, post.reposts.count),
                    StatisticItem(StatisticType.VIEWS, post.views.count)
                ),
                isLiked = post.likes.userLikes > 0
            )
            result.add(feedPost)
        }

        return result
    }

    private fun mapTimestampToDate(timestamp: Long): String {
        val date = Date(timestamp * 1000)
        return SimpleDateFormat("d MMMM yyyy, hh:mm", Locale.getDefault()).format(date)
    }

    fun mapResponseToComments(commentsResponseDto: CommentsResponseDto): List<PostComment> {
        val result = mutableListOf<PostComment>()
        val comments = commentsResponseDto.content.items
        val profiles = commentsResponseDto.content.profiles
        for (comment in comments) {
            if (comment.text.isBlank()) continue
            val author = profiles.firstOrNull { it.id == comment.authorId } ?: continue
            val postComment = PostComment(
                id = comment.id,
                authorName = "${author.firstName} ${author.lastName}",
                authorAvatarUrl = author.avatarUrl,
                commentText = comment.text,
                publicationDate = mapTimestampToDate(comment.date)
            )
            result.add(postComment)
        }
        return result
    }
}