package com.emirkaya.movieapp.presentation.ui.screens.moviedetailscreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.emirkaya.movieapp.R
import com.emirkaya.movieapp.presentation.ui.screens.moviedetailscreen.components.MovieDetailBody
import com.emirkaya.movieapp.presentation.ui.screens.moviedetailscreen.components.MovieDetailHeader
import com.emirkaya.movieapp.presentation.ui.theme.Dimensions
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState


@Composable
fun MovieDetailRoute(
    movieId: Int,
    onNavigateToActorDetail: (Int) -> Unit,
    onNavigateToSimilarMovieDetail: (Int) -> Unit,
    viewModel: MovieDetailViewModel = hiltViewModel()
) {
    MovieDetailScreen(
        movieId = movieId,
        onEvent = viewModel::handleEvent,
        onNavigateToActorDetail = onNavigateToActorDetail,
        onNavigateToSimilarMovieDetail = onNavigateToSimilarMovieDetail,
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MovieDetailScreen(
    movieId: Int,
    onNavigateToActorDetail: (Int) -> Unit,
    onNavigateToSimilarMovieDetail: (Int) -> Unit,
    onEvent: (MovieDetailUiEvent) -> Unit,
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

                    MovieDetailHeader(uiState, pagerState, movie, onEvent, movieId)

                    Spacer(modifier = Modifier.height(Dimensions.spacerHeightSmall))

                    MovieDetailBody(movie, uiState, onEvent, context, onNavigateToActorDetail = onNavigateToActorDetail, onNavigateToSimilarMovieDetail = onNavigateToSimilarMovieDetail)
                }
            }
        }
    }
}











