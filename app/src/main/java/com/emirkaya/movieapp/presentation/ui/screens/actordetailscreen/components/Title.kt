package com.emirkaya.movieapp.presentation.ui.screens.actordetailscreen.components

import androidx.compose.foundation.ScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import com.emirkaya.movieapp.presentation.ui.theme.ActorDetailDimensions
import kotlin.math.pow

@Composable
fun Title(
    scroll: ScrollState,
    headerHeightPx: Float,
    toolbarHeightPx: Float,
    actorName: String
) {
    var titleHeightPx by remember { mutableFloatStateOf(0f) }

    Text(
        text = actorName,
        fontSize = ActorDetailDimensions.fontSizeActorName,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        modifier = Modifier
            .graphicsLayer {
                val collapseRange: Float = (headerHeightPx - toolbarHeightPx)
                val collapseFraction: Float = (scroll.value / collapseRange).coerceIn(0f, 1f)

                val titleY: Float = (1f - collapseFraction).pow(2) *
                        (headerHeightPx - titleHeightPx - ActorDetailDimensions.horizontalPadding.toPx()) +
                        2 * collapseFraction * (1 - collapseFraction) * headerHeightPx / 2 +
                        collapseFraction.pow(2) * (toolbarHeightPx / 2 - titleHeightPx / 2)

                val titleX: Float =
                    (1f - collapseFraction).pow(2) * (ActorDetailDimensions.horizontalPadding.toPx()) +
                            2 * collapseFraction * (1 - collapseFraction) * ActorDetailDimensions.horizontalPadding.toPx() * 5 / 4 +
                            collapseFraction.pow(2) * ActorDetailDimensions.horizontalPadding.toPx()

                translationY = titleY
                translationX = titleX
            }
            .onGloballyPositioned { titleHeightPx = it.size.height.toFloat() }
    )
}