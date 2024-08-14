package com.emirkaya.movieapp.domain.model

import com.emirkaya.movieapp.data.model.moviemodel.MovieItem

data class FavoriteMovie(
    val movieId: Int,
    val posterPath: String?,
    val title: String?,
    val voteAverage: Double?,
    val releaseDate: String?
)

fun FavoriteMovie.toMovieItem(): MovieItem {
    return MovieItem(
        id = movieId,
        posterPath = posterPath!!,
        title = title!!,
        voteAverage = voteAverage!!,
        releaseDate = releaseDate!!
    )
}