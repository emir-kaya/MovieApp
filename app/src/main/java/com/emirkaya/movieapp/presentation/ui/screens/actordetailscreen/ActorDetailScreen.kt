package com.emirkaya.movieapp.presentation.ui.screens.actordetailscreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.emirkaya.movieapp.R
import com.emirkaya.movieapp.data.model.actordetail.ActorDetailResponse
import com.emirkaya.movieapp.data.model.moviecredit.Cast
import com.emirkaya.movieapp.presentation.ui.theme.ActorDetailDimensions
import com.emirkaya.movieapp.util.ImageUtil.buildImageUrl
import kotlin.math.pow


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

@Composable
private fun Header(scroll: ScrollState, headerHeightPx: Float, imageUrl: String?) {
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

@Composable
private fun Body(
    scrollState: ScrollState,
    actorDetail: ActorDetailResponse,
    headerHeight: Dp,
    movieCredits: List<Cast>,
    navController: NavHostController,
    isExpanded: Boolean,
    isBiographyLong: Boolean,
    onExpandToggle: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.verticalScroll(scrollState)
    ) {
        Spacer(Modifier.height(headerHeight))

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = ActorDetailDimensions.cardPadding),
            shape = RoundedCornerShape(topStart = ActorDetailDimensions.cardCornerRadius, topEnd = ActorDetailDimensions.cardCornerRadius),
            elevation = ActorDetailDimensions.cardElevation
        ) {
            Column(modifier = Modifier.padding(ActorDetailDimensions.cardPadding)) {
                ActorDetailHeader(actorDetail)
                Spacer(modifier = Modifier.height(ActorDetailDimensions.spacerHeightMedium))

                Text(
                    text = "Biography",
                    fontSize = ActorDetailDimensions.fontSizeTitle,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(ActorDetailDimensions.spacerHeightSmall))

                Card(
                    shape = RoundedCornerShape(ActorDetailDimensions.cardCornerRadius),
                    backgroundColor = Color.LightGray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateContentSize()
                ) {
                    Column(modifier = Modifier.padding(ActorDetailDimensions.cardPadding)) {
                        Text(
                            text = actorDetail.biography ?: stringResource(R.string.unknown),
                            fontSize = ActorDetailDimensions.fontSizeBiography,
                            color = Color.Gray,
                            maxLines = if (isExpanded) Int.MAX_VALUE else 2,
                            overflow = if (isExpanded) TextOverflow.Visible else TextOverflow.Ellipsis
                        )

                        if (isBiographyLong) {
                            IconButton(
                                onClick = onExpandToggle,
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

                Spacer(modifier = Modifier.height(ActorDetailDimensions.spacerHeightMedium))

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = ActorDetailDimensions.horizontalPadding)
                ) {
                    items(movieCredits) { movie ->
                        MovieCreditCard(movie, navController)
                    }
                }
            }
        }
    }
}

@Composable
private fun ActorDetailHeader(actorDetail: ActorDetailResponse) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = ActorDetailDimensions.spacerHeightSmall),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = actorDetail.birthday ?: stringResource(R.string.unknown),
            fontSize = ActorDetailDimensions.fontSizeBiography,
            color = Color.Gray,
            modifier = Modifier.padding(end = ActorDetailDimensions.spacerHeightSmall)
        )

        Divider(
            color = Color.Gray, thickness = ActorDetailDimensions.dividerThickness, modifier = Modifier
                .height(ActorDetailDimensions.dividerHeight)
                .width(ActorDetailDimensions.dividerThickness)
        )

        Spacer(modifier = Modifier.width(ActorDetailDimensions.spacerHeightSmall))

        Text(
            text = actorDetail.placeOfBirth ?: stringResource(R.string.unknown),
            fontSize = ActorDetailDimensions.fontSizeBiography,
            color = Color.Gray
        )

        actorDetail.deathday?.let {
            Spacer(modifier = Modifier.width(ActorDetailDimensions.spacerHeightSmall))

            Divider(
                color = Color.Gray, thickness = ActorDetailDimensions.dividerThickness, modifier = Modifier
                    .height(ActorDetailDimensions.dividerHeight)
                    .width(ActorDetailDimensions.dividerThickness)
            )

            Spacer(modifier = Modifier.width(ActorDetailDimensions.spacerHeightSmall))

            Text(
                text = it.toString(),
                fontSize = ActorDetailDimensions.fontSizeBiography,
                color = Color.Gray
            )
        }
    }
}

@Composable
private fun Toolbar(
    scroll: ScrollState,
    headerHeightPx: Float,
    toolbarHeightPx: Float,
    navController: NavHostController
) {
    val toolbarBottom = headerHeightPx - toolbarHeightPx
    val showToolbar by remember { derivedStateOf { scroll.value >= toolbarBottom } }

    AnimatedVisibility(
        visible = showToolbar,
        enter = fadeIn(animationSpec = tween(300)),
        exit = fadeOut(animationSpec = tween(300))
    ) {
        TopAppBar(
            title = {},
            backgroundColor = Color.LightGray,
            contentColor = Color.White,
            elevation = 0.dp
        )
    }
}

@Composable
private fun Title(
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
