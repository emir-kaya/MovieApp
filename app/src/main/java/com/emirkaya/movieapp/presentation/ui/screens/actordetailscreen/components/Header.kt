package com.emirkaya.movieapp.presentation.ui.screens.actordetailscreen.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.emirkaya.movieapp.presentation.ui.theme.ActorDetailDimensions
import com.emirkaya.movieapp.util.ImageUtil.buildImageUrl

@Composable
fun Header(scroll: ScrollState, headerHeightPx: Float, imageUrl: String?) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(ActorDetailDimensions.headerHeight)
            .graphicsLayer { translationY = -scroll.value.toFloat() / 2f }
    ) {
        AsyncImage(
            model = buildImageUrl(imageUrl ?: ""),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color(0xAA000000)),
                        startY = 3 * headerHeightPx / 4
                    )
                )
        )
    }
}