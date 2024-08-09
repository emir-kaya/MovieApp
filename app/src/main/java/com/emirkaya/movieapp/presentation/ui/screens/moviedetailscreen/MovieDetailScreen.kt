package com.emirkaya.movieapp.presentation.ui.screens.moviedetailscreen

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.emirkaya.movieapp.util.Constants
import com.emirkaya.movieapp.R
import com.emirkaya.movieapp.presentation.ui.theme.Dimensions
import com.emirkaya.movieapp.util.ImageUtil.buildImageUrl
import com.google.accompanist.pager.ExperimentalPagerApi
import com.skydoves.landscapist.glide.GlideImage
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState


@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@Composable
fun MovieDetailScreen(
    navController: NavHostController,
    movieId: Int,
    viewModel: MovieDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(movieId) {
        viewModel.getMovieDetailAndTeaserKey(movieId)
    }

    val pagerState = rememberPagerState()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        when {
            uiState.isLoading -> {
                CircularProgressIndicator()
            }
            uiState.error != null -> {
                Text(
                    text = uiState.error ?: stringResource(id = R.string.error_occurred),
                    color = Color.Red
                )
            }
            uiState.movieDetail != null -> {
                val movie = uiState.movieDetail

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(Dimensions.horizontalPadding)
                ) {
                    Spacer(modifier = Modifier.height(Dimensions.spacerHeightLarge))

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

                    Text(
                        text = movie?.title ?: stringResource(id = R.string.title_not_available),
                        fontSize = Dimensions.fontSizeTitle,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )

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
                                        colors = ChipDefaults.chipColors(backgroundColor = Color.LightGray)
                                    ) {
                                        Text(text = name, color = Color.Black)
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
                        horizontalArrangement = Arrangement.Center
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

                    Spacer(modifier = Modifier.height(Dimensions.spacerHeightSmall))

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
                                    text = movie?.releaseDate ?: stringResource(id = R.string.release_date_not_available),
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
                                        color = Color.Black,
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
                                backgroundColor = Color.LightGray,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .animateContentSize()
                            ) {
                                Column(
                                    modifier = Modifier.padding(Dimensions.cardPadding)
                                ) {
                                    val isOverviewLong = (uiState.movieDetail?.overview?.length ?: 0) > 100
                                    Text(
                                        text = uiState.movieDetail?.overview ?: stringResource(id = R.string.overview_not_available),
                                        fontSize = Dimensions.fontSizeMedium,
                                        color = Color.Gray,
                                        maxLines = if (uiState.isExpanded) Int.MAX_VALUE else 2,
                                        overflow = if (uiState.isExpanded) TextOverflow.Visible else TextOverflow.Ellipsis
                                    )
                                    if (isOverviewLong) {
                                        IconButton(
                                            onClick = { viewModel.handleEvent(MovieDetailUiEvent.ToggleOverviewExpansion) },
                                            modifier = Modifier.align(Alignment.End)
                                        ) {
                                            Icon(
                                                imageVector = if (uiState.isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                                contentDescription = if (uiState.isExpanded) stringResource(id = R.string.read_less) else stringResource(id = R.string.read_more)
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
                                            .background(Color.Black.copy(Dimensions.playButtonBackgroundAlpha), CircleShape)
                                            .padding(Dimensions.playButtonPadding),
                                        tint = Color.White
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}





@Preview(showBackground = true)
@Composable
fun MovieDetailScreenPreview() {
    MovieDetailScreen(navController = rememberNavController(), movieId = 1)
}


