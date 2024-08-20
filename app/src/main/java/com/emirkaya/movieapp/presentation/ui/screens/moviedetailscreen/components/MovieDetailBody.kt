package com.emirkaya.movieapp.presentation.ui.screens.moviedetailscreen.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController
import com.emirkaya.movieapp.R
import com.emirkaya.movieapp.data.model.moviedetailmodel.MovieDetailResponse
import com.emirkaya.movieapp.presentation.ui.screens.moviedetailscreen.MovieDetailUiEvent
import com.emirkaya.movieapp.presentation.ui.screens.moviedetailscreen.MovieDetailUiState
import com.emirkaya.movieapp.presentation.ui.theme.Dimensions
import com.emirkaya.movieapp.util.Constants
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun MovieDetailBody(
    movie: MovieDetailResponse?,
    uiState: MovieDetailUiState,
    onEvent: (MovieDetailUiEvent) -> Unit,
    context: Context,
    onNavigateToActorDetail: (Int) -> Unit,
    onNavigateToSimilarMovieDetail: (Int) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimensions.horizontalPadding)
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = Dimensions.spacerHeightSmall),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.runtime, movie?.runtime ?: 0),
                    fontSize = Dimensions.fontSizeMedium,
                    color = Color.Gray,
                    modifier = Modifier.padding(end = Dimensions.chipPadding)
                )

                Divider(
                    color = Color.Gray, thickness = Dimensions.dividerThickness, modifier = Modifier
                        .height(Dimensions.dividerHeight)
                        .width(Dimensions.dividerThickness)
                )

                Spacer(modifier = Modifier.width(Dimensions.chipPadding))

                Text(
                    text = movie?.releaseDate
                        ?: stringResource(id = R.string.release_date_not_available),
                    fontSize = Dimensions.fontSizeMedium,
                    color = Color.Gray
                )
            }
        }

        item { Spacer(modifier = Modifier.height(Dimensions.spacerHeightSmall)) }

        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                uiState.productionCompany?.let {
                    Text(
                        text = it,
                        fontSize = Dimensions.fontSizeMedium,
                        color = Color.Gray,
                        modifier = Modifier.padding(end = Dimensions.chipPadding)
                    )
                }

                Spacer(modifier = Modifier.width(Dimensions.chipPadding))

                Divider(
                    color = Color.Gray, thickness = Dimensions.dividerThickness, modifier = Modifier
                        .height(Dimensions.dividerHeight)
                        .width(Dimensions.dividerThickness)
                )

                Spacer(modifier = Modifier.width(Dimensions.chipPadding))

                uiState.language?.let { language ->
                    Text(
                        text = language,
                        fontSize = Dimensions.fontSizeMedium,
                        color = Color.Gray
                    )
                }
            }
        }

        item { Spacer(modifier = Modifier.height(Dimensions.spacerHeightMedium)) }

        item {
            Card(
                shape = RoundedCornerShape(Dimensions.cardCornerRadius),
                backgroundColor = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize()
            ) {
                Column(
                    modifier = Modifier.padding(Dimensions.cardPadding)
                ) {
                    val isOverviewLong = (uiState.movieDetail?.overview?.length ?: 0) > 100
                    Text(
                        text = uiState.movieDetail?.overview
                            ?: stringResource(id = R.string.overview_not_available),
                        fontSize = Dimensions.fontSizeMedium,
                        color = Color.LightGray,
                        maxLines = if (uiState.isExpanded) Int.MAX_VALUE else 2,
                        overflow = if (uiState.isExpanded) TextOverflow.Visible else TextOverflow.Ellipsis
                    )
                    if (isOverviewLong) {
                        IconButton(
                            onClick = { onEvent(MovieDetailUiEvent.ToggleOverviewExpansion) },
                            modifier = Modifier.align(Alignment.End)
                        ) {
                            Icon(
                                imageVector = if (uiState.isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                contentDescription = if (uiState.isExpanded) stringResource(id = R.string.read_less) else stringResource(
                                    id = R.string.read_more
                                )
                            )
                        }
                    }
                }
            }
        }

        item { Spacer(modifier = Modifier.height(Dimensions.spacerHeightMedium)) }

        item {
            uiState.teaserVideoKey?.let { videoKey ->
                val thumbnailUrl = "${Constants.IMG_YOUTUBE}$videoKey/hqdefault.jpg"
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(Dimensions.imageHeight)
                        .clip(RoundedCornerShape(Dimensions.cardCornerRadius))
                        .clickable {
                            val intent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("${Constants.YOUTUBE_VIDEO_URL}$videoKey")
                            )
                            context.startActivity(intent)
                        }
                ) {
                    GlideImage(
                        imageModel = thumbnailUrl,
                        contentDescription = null,
                        modifier = Modifier.matchParentSize()
                    )
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = stringResource(id = R.string.play_video),
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(Dimensions.playButtonSize)
                            .background(
                                Color.Black.copy(Dimensions.playButtonBackgroundAlpha),
                                CircleShape
                            )
                            .padding(Dimensions.playButtonPadding),
                        tint = Color.White
                    )
                }
            }
        }


        item {
            uiState.similarMovies?.let { similarMovies ->
                Spacer(modifier = Modifier.height(Dimensions.spacerHeight24))
                Text(
                    text = stringResource(R.string.similar_movies),
                    fontSize = Dimensions.fontSizeMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.LightGray
                )
                Spacer(modifier = Modifier.height(Dimensions.spacerHeightSmall))
                LazyRow(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(similarMovies.size) { index ->
                        SimilarMovieCard(
                            movie = similarMovies[index],
                            onNavigateToSimilarMovieDetail = onNavigateToSimilarMovieDetail
                        )
                    }
                }
            }
        }
        item {
            uiState.movieDetailActors?.let { movieDetailActors ->
                Spacer(modifier = Modifier.height(Dimensions.spacerHeight24))
                Text(
                    text = stringResource(R.string.cast),
                    fontSize = Dimensions.fontSizeMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.LightGray
                )
                Spacer(modifier = Modifier.height(Dimensions.spacerHeightSmall))
                LazyRow(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(movieDetailActors.size) { index ->
                        MovieDetailActorCard(
                            actor = movieDetailActors[index],
                            onNavigateToActorDetail = onNavigateToActorDetail
                        )
                    }
                }
            }
        }
    }
}
