package com.emirkaya.movieapp.presentation.ui.screens.moviedetailscreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.emirkaya.movieapp.R
import com.emirkaya.movieapp.data.model.moviedetailmodel.MovieDetailResponse
import com.emirkaya.movieapp.presentation.ui.screens.moviedetailscreen.MovieDetailUiEvent
import com.emirkaya.movieapp.presentation.ui.screens.moviedetailscreen.MovieDetailUiState
import com.emirkaya.movieapp.presentation.ui.theme.Dimensions
import com.emirkaya.movieapp.util.ImageUtil.buildImageUrl
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import com.skydoves.landscapist.glide.GlideImage


@Composable
@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
fun MovieDetailHeader(
    uiState: MovieDetailUiState,
    pagerState: PagerState,
    movie: MovieDetailResponse?,
    onEvent: (MovieDetailUiEvent) -> Unit,
    movieId: Int
) {
    val backdrops = uiState.backdrops ?: emptyList()

    if (backdrops.isNotEmpty()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            HorizontalPager(
                count = backdrops.size,
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimensions.imageHeight)
            ) { page ->
                val backdropPath = backdrops[page]
                GlideImage(
                    imageModel = buildImageUrl(backdropPath),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(Dimensions.imageHeight)
                        .clip(RoundedCornerShape(Dimensions.cardCornerRadius))
                )
            }
            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = Dimensions.spacerHeightSmall)
            )
        }
    }

    Spacer(modifier = Modifier.height(Dimensions.spacerHeightMedium))

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = movie?.title ?: stringResource(id = R.string.title_not_available),
            fontSize = Dimensions.fontSizeTitle,
            fontWeight = FontWeight.Bold,
            color = Color.LightGray,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }

    Spacer(modifier = Modifier.height(Dimensions.spacerHeightMedium))

    movie?.genres?.let { genres ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = Dimensions.spacerHeightSmall),
            horizontalArrangement = Arrangement.Center
        ) {
            genres.forEach { genre ->
                genre?.name?.let { name ->
                    Chip(
                        onClick = {},
                        modifier = Modifier.padding(Dimensions.chipPadding),
                        shape = RoundedCornerShape(Dimensions.cardCornerRadius),
                        colors = ChipDefaults.chipColors(backgroundColor = MaterialTheme.colorScheme.onSecondary)
                    ) {
                        Text(text = name, color = Color.LightGray)
                    }
                }
            }
        }
    }

    Spacer(modifier = Modifier.height(Dimensions.spacerHeightSmall))


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = Dimensions.spacerHeightSmall),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.weight(0.6f))
        Row(
            modifier = Modifier
                .weight(3f)
                .wrapContentWidth(Alignment.CenterHorizontally),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(uiState.fullStars) {
                Image(
                    painter = painterResource(id = R.drawable.ic_fullstar),
                    contentDescription = null,
                    modifier = Modifier.size(Dimensions.starIconSize)
                )
            }
            repeat(uiState.halfStars) {
                Image(
                    painter = painterResource(id = R.drawable.ic_halfstar),
                    contentDescription = null,
                    modifier = Modifier.size(Dimensions.starIconSize)
                )
            }
            repeat(uiState.emptyStars) {
                Image(
                    painter = painterResource(id = R.drawable.ic_emptystar),
                    contentDescription = null,
                    modifier = Modifier.size(Dimensions.starIconSize)
                )
            }
        }

        androidx.compose.material3.IconButton(
            onClick = {
                if (uiState.isFavorite) {
                    onEvent(MovieDetailUiEvent.RemoveFavorite(movieId))
                } else {
                    onEvent(MovieDetailUiEvent.AddFavorite(movieId))
                }
            },
            modifier = Modifier
                .wrapContentWidth(Alignment.End)
                .padding(end = 16.dp)
        ) {
            androidx.compose.material3.Icon(
                modifier = Modifier
                    .padding(3.dp)
                    .wrapContentSize(),
                painter = painterResource(
                    id = if (uiState.isFavorite) R.drawable.ic_favorite_filled else R.drawable.ic_favorite_border
                ),
                contentDescription = if (uiState.isFavorite) "Remove from favorites" else "Add to favorites",
                tint = Color.Black
            )
        }
    }
}
