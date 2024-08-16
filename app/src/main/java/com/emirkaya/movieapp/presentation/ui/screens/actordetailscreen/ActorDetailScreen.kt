package com.emirkaya.movieapp.presentation.ui.screens.actordetailscreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
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
@Composable
fun ActorDetailRoute(
    navController: NavHostController,
    actorId: Int,
    viewModel: ActorDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val movieCredits = uiState.movieCredits ?: emptyList()

    ActorDetailScreen(
        navController = navController,
        actorId = actorId,
        movieCredits = movieCredits,
        viewModel = viewModel
    )
}

@Composable
fun ActorDetailScreen(
    navController: NavHostController,
    actorId: Int,
    movieCredits: List<Cast>,
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

        Box(modifier = Modifier.fillMaxSize()) {
            Body(
                scrollState = scrollState,
                actorDetail = actorDetail,
                headerHeight = headerHeight,
                movieCredits = uiState.movieCredits ?: emptyList(),
                navController = navController,
                isExpanded = isExpanded,
                isBiographyLong = viewModel.isBiographyLong,
                onExpandToggle = { viewModel.toggleBiographyExpansion() }
            )
            Header(scrollState, headerHeightPx, actorDetail.profilePath)
            Toolbar(scrollState, headerHeightPx, toolbarHeightPx, navController)
            Title(scrollState, headerHeightPx, toolbarHeightPx, actorDetail.name!!)
        }
    }
}









