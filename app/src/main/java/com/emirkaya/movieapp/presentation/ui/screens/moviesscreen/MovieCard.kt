package com.emirkaya.movieapp.presentation.ui.screens.moviesscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emirkaya.movieapp.Constants
import com.emirkaya.movieapp.R
import com.emirkaya.movieapp.data.model.MovieItem
import com.skydoves.landscapist.glide.GlideImage
import java.util.Locale

@Composable
fun MovieCard(movie: MovieItem) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(380.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            GlideImage(
                imageModel = "${Constants.BASE_IMG_URL}${movie.posterPath}",
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = movie.title ?: stringResource(R.string.unknown),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(R.string.rating, movie.voteAverage),
                fontSize = 14.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(R.string.date, movie.releaseDate),
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewMovieCard() {
    val exampleMovie = MovieItem(
        id = 1,
        title = "Deadpool & Wolverine",
        overview = "A mind-bending thriller",
        posterPath = "/8cdWjvZQUExUUTzyp4t6EDMubfO.jpg",
        releaseDate = "2024-07-24",
        voteAverage = 7.9
    )
    MovieCard(movie = exampleMovie)
}