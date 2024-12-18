package com.emirkaya.movieapp.presentation.ui.screens.actordetailscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.emirkaya.movieapp.data.model.moviecredit.Cast
import com.emirkaya.movieapp.presentation.ui.screens.actordetailscreen.components.Body
import com.emirkaya.movieapp.presentation.ui.screens.actordetailscreen.components.Header
import com.emirkaya.movieapp.presentation.ui.screens.actordetailscreen.components.Title
import com.emirkaya.movieapp.presentation.ui.screens.actordetailscreen.components.Toolbar
import com.emirkaya.movieapp.presentation.ui.theme.ActorDetailDimensions
import com.emirkaya.movieapp.util.Constants

@Composable
fun ActorDetailRoute(
    onNavigateToActorMovieDetail: (Int) -> Unit,
    actorId: Int,
    viewModel: ActorDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val movieCredits = uiState.movieCredits ?: emptyList()

    ActorDetailScreen(
        actorId = actorId,
        onNavigateToActorMovieDetail = onNavigateToActorMovieDetail,
        viewModel = viewModel
    )
}

@Composable
fun ActorDetailScreen(
    onNavigateToActorMovieDetail: (Int) -> Unit,
    actorId: Int,
    viewModel: ActorDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val isExpanded by viewModel.isExpanded.collectAsState()

    LaunchedEffect(actorId) {
        viewModel.getActorDetail(actorId)
    }

    uiState.actorDetail?.let { actorDetail ->
        val scrollState = rememberScrollState()
        val headerHeight = ActorDetailDimensions.headerHeight
        val headerHeightPx = with(LocalDensity.current) { headerHeight.toPx() }
        val toolbarHeight = ActorDetailDimensions.toolbarHeight
        val toolbarHeightPx = with(LocalDensity.current) { toolbarHeight.toPx() }

        Box(
            modifier = Modifier.fillMaxSize()
                .background(MaterialTheme.colorScheme.background)) {
            Body(
                scrollState = scrollState,
                actorDetail = actorDetail,
                headerHeight = headerHeight,
                movieCredits = uiState.movieCredits ?: emptyList(),
                onMovieClick = onNavigateToActorMovieDetail,
                isExpanded = isExpanded,
                isBiographyLong = viewModel.isBiographyLong,
                onExpandToggle = { viewModel.toggleBiographyExpansion() }
            )
            Header(scrollState, headerHeightPx, actorDetail.profilePath)
            Toolbar(scrollState, headerHeightPx, toolbarHeightPx)
            Title(scrollState, headerHeightPx, toolbarHeightPx, actorDetail.name!!)
        }
    }
}









