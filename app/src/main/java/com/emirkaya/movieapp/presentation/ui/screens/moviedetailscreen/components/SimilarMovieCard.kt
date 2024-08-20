package com.emirkaya.movieapp.presentation.ui.screens.moviedetailscreen.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController
import com.emirkaya.movieapp.R
import com.emirkaya.movieapp.data.model.similarmovies.SimilarMovie
import com.emirkaya.movieapp.presentation.ui.theme.MovieCardDimensions
import com.emirkaya.movieapp.util.Constants
import com.emirkaya.movieapp.util.ImageUtil.buildImageUrl
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun SimilarMovieCard(movie: SimilarMovie, onNavigateToSimilarMovieDetail: (Int) -> Unit) {
    Card(
        shape = RoundedCornerShape(MovieCardDimensions.cardCornerRadius),
        elevation = CardDefaults.cardElevation(defaultElevation = MovieCardDimensions.cardElevation),
        modifier = Modifier
            .padding(MovieCardDimensions.cardPadding)
            .width(MovieCardDimensions.cardWidth)
            .height(MovieCardDimensions.cardHeight)
            .clickable { onNavigateToSimilarMovieDetail(movie.id) }
    ) {
        Column(modifier = Modifier.padding(MovieCardDimensions.cardPadding)) {
            GlideImage(
                imageModel = buildImageUrl(movie.posterPath),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(MovieCardDimensions.imageHeight)
                    .clip(RoundedCornerShape(MovieCardDimensions.cardCornerRadius))
            )
            Spacer(modifier = Modifier.height(MovieCardDimensions.spacerHeightMedium))
            Text(
                text = movie.title ?: stringResource(R.string.unknown),
                fontWeight = FontWeight.Bold,
                fontSize = MovieCardDimensions.titleFontSize,
                color = Color.Black,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MovieCardDimensions.horizontalPadding)
            )
            Spacer(modifier = Modifier.height(MovieCardDimensions.spacerHeightSmall))
            Text(
                text = stringResource(R.string.rating, movie.voteAverage!!),
                fontSize = MovieCardDimensions.subtitleFontSize,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(MovieCardDimensions.spacerHeightSmall))
            Text(
                text = stringResource(R.string.date, movie.releaseDate!!),
                fontSize = MovieCardDimensions.subtitleFontSize,
                color = Color.Gray
            )
        }
    }
}
