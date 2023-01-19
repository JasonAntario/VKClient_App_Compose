package com.example.vkclientappcompose

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vkclientappcompose.ui.theme.VKClientAppComposeTheme


@Composable
fun PostCard() {
    Card {
        Column(modifier = Modifier.padding(8.dp)) {
            PostHeader()
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = stringResource(R.string.text_placeholder))
            Spacer(modifier = Modifier.size(8.dp))
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.post_content_image),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
            Spacer(modifier = Modifier.size(8.dp))
            PostStatistics()
        }
    }
}

@Composable
fun PostHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            painter = painterResource(id = R.drawable.post_comunity_thumbnail),
            contentDescription = null
        )
        Spacer(modifier = Modifier.size(8.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Group name",
                color = MaterialTheme.colors.onPrimary
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = "18:04",
                color = MaterialTheme.colors.onSecondary
            )
        }
        Spacer(modifier = Modifier.size(8.dp))
        Icon(
            imageVector = Icons.Rounded.MoreVert,
            contentDescription = null,
            tint = MaterialTheme.colors.onSecondary
        )
    }
}

@Composable
fun PostStatistics() {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.SpaceBetween) {
            IconWithText(iconResId = R.drawable.ic_like, text = "75040")
            IconWithText(iconResId = R.drawable.ic_comment, text = "97")
            IconWithText(iconResId = R.drawable.ic_share, text = "50")
        }
        Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.End) {
            IconWithText(iconResId = R.drawable.ic_views, text = "5040")
        }
    }
}

@Composable
fun IconWithText(
    @DrawableRes iconResId: Int,
    text: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = null,
            tint = MaterialTheme.colors.onSecondary
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = text,
            color = MaterialTheme.colors.onSecondary
        )
    }
}


@Preview
@Composable
fun PreviewPostCardLight() {
    VKClientAppComposeTheme(darkTheme = false) {
        PostCard()
    }
}

@Preview
@Composable
fun PreviewPostCardDark() {
    VKClientAppComposeTheme(darkTheme = true) {
        PostCard()
    }
}