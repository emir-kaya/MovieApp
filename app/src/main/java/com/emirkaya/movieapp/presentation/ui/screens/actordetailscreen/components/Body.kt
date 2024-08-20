package com.emirkaya.movieapp.presentation.ui.screens.actordetailscreen.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavHostController
import com.emirkaya.movieapp.R
import com.emirkaya.movieapp.data.model.actordetail.ActorDetailResponse
import com.emirkaya.movieapp.data.model.moviecredit.Cast

import com.emirkaya.movieapp.presentation.ui.theme.ActorDetailDimensions


@Composable
fun Body(
    scrollState: ScrollState,
    actorDetail: ActorDetailResponse,
    headerHeight: Dp,
    movieCredits: List<Cast>,
    onMovieClick: (Int) -> Unit,
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
                        MovieCreditCard(movie, onClick = { onMovieClick(movie.id!!) })
                    }
                }
            }
        }
    }
}