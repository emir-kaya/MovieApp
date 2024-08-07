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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.emirkaya.movieapp.Constants
import com.emirkaya.movieapp.R
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
    var isExpanded by remember { mutableStateOf(false) }
    val maxLinesForOverview = 2
    val context = LocalContext.current

    LaunchedEffect(movieId) {
        viewModel.getMovieDetailAndTeaserKey(movieId, Constants.BEARER_TOKEN)
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
                        .padding(16.dp)
                ) {
                    Spacer(modifier = Modifier.height(34.dp))

                    val backdrops = uiState.backdrops?.map { it?.filePath }?.filterNotNull() ?: emptyList()

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
                                    .height(200.dp)
                            ) { page ->
                                val backdropPath = backdrops[page]
                                GlideImage(
                                    imageModel = buildImageUrl(backdropPath),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(200.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                )
                            }
                            HorizontalPagerIndicator(
                                pagerState = pagerState,
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .padding(top = 8.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = movie?.title ?: stringResource(id = R.string.title_not_available),
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    movie?.genres?.let { genres ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            genres.forEach { genre ->
                                genre?.name?.let { name ->
                                    Chip(
                                        onClick = {},
                                        modifier = Modifier.padding(4.dp),
                                        shape = RoundedCornerShape(8.dp),
                                        colors = ChipDefaults.chipColors(backgroundColor = Color.LightGray)
                                    ) {
                                        Text(text = name, color = Color.Black)
                                    }
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        val voteAverage = movie?.voteAverage ?: 0.0
                        val fullStars = voteAverage.toInt() / 2
                        val halfStars = if (voteAverage % 2 >= 1) 1 else 0
                        val emptyStars = 5 - fullStars - halfStars

                        repeat(fullStars) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_fullstar),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        repeat(halfStars) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_halfstar),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        repeat(emptyStars) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_emptystar),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        item {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 8.dp),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = stringResource(id = R.string.runtime, movie?.runtime ?: 0),
                                    fontSize = 16.sp,
                                    color = Color.Gray,
                                    modifier = Modifier.padding(end = 8.dp)
                                )

                                Divider(
                                    color = Color.Gray, thickness = 1.dp, modifier = Modifier
                                        .height(16.dp)
                                        .width(1.dp)
                                )

                                Spacer(modifier = Modifier.width(8.dp))

                                Text(
                                    text = movie?.releaseDate ?: stringResource(id = R.string.release_date_not_available),
                                    fontSize = 16.sp,
                                    color = Color.Gray
                                )
                            }
                        }

                        item { Spacer(modifier = Modifier.height(8.dp)) }

                        item {
                            val productionCompany = movie?.productionCompanies?.firstOrNull()
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                productionCompany?.name?.let {
                                    Text(
                                        text = it,
                                        fontSize = 16.sp,
                                        color = Color.Black,
                                        modifier = Modifier.padding(end = 8.dp)
                                    )
                                }

                                Spacer(modifier = Modifier.width(8.dp))

                                Divider(
                                    color = Color.Gray, thickness = 1.dp, modifier = Modifier
                                        .height(16.dp)
                                        .width(1.dp)
                                )

                                Spacer(modifier = Modifier.width(8.dp))

                                movie?.spokenLanguages?.firstOrNull()?.englishName?.let { language ->
                                    Text(
                                        text = language,
                                        fontSize = 16.sp,
                                        color = Color.Gray
                                    )
                                }
                            }
                        }

                        item { Spacer(modifier = Modifier.height(16.dp)) }

                        item {
                            Card(
                                shape = RoundedCornerShape(8.dp),
                                backgroundColor = Color.LightGray,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .animateContentSize()
                            ) {
                                Column(
                                    modifier = Modifier.padding(16.dp)
                                ) {
                                    val isOverviewLong = (movie?.overview?.length ?: 0) > 100
                                    Text(
                                        text = movie?.overview ?: stringResource(id = R.string.overview_not_available),
                                        fontSize = 16.sp,
                                        color = Color.Gray,
                                        maxLines = if (isExpanded) Int.MAX_VALUE else maxLinesForOverview,
                                        overflow = if (isExpanded) TextOverflow.Visible else TextOverflow.Ellipsis
                                    )
                                    if (isOverviewLong) {
                                        IconButton(
                                            onClick = { isExpanded = !isExpanded },
                                            modifier = Modifier.align(Alignment.End)
                                        ) {
                                            Icon(
                                                imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                                contentDescription = if (isExpanded) stringResource(id = R.string.read_less) else stringResource(id = R.string.read_more)
                                            )
                                        }
                                    }
                                }
                            }
                        }

                        item { Spacer(modifier = Modifier.height(16.dp)) }

                        item {
                            uiState.teaserVideoKey?.let { videoKey ->
                                val thumbnailUrl = "${Constants.IMG_YOUTUBE}$videoKey/hqdefault.jpg"
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(200.dp)
                                        .clip(RoundedCornerShape(8.dp))
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
                                            .size(64.dp)
                                            .background(Color.Black.copy(alpha = 0.5f), CircleShape)
                                            .padding(8.dp),
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

fun buildImageUrl(path: String): String {
    return "${Constants.BASE_IMG_URL}$path"
}

