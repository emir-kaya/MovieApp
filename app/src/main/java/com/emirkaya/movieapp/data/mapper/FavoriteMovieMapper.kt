package com.emirkaya.movieapp.data.mapper

import com.emirkaya.movieapp.data.entity.FavoriteMovieEntity
import com.emirkaya.movieapp.domain.model.FavoriteMovie

fun FavoriteMovie.toEntity(): FavoriteMovieEntity {
    return FavoriteMovieEntity(
        movieId = this.movieId,
        posterPath = this.posterPath,
        title = this.title,
        voteAverage = this.voteAverage,
        releaseDate = this.releaseDate
    )
}

fun FavoriteMovieEntity.toDomain(): FavoriteMovie {
    return FavoriteMovie(
        movieId = this.movieId,
        posterPath = this.posterPath,
        title = this.title,
        voteAverage = this.voteAverage,
        releaseDate = this.releaseDate
    )
}