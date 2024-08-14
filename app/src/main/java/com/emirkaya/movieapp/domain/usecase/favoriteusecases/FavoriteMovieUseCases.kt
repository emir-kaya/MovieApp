package com.emirkaya.movieapp.domain.usecase.favoriteusecases

data class FavoriteMovieUseCases(
    val addFavoriteMovie: AddFavoriteMovie,
    val removeFavoriteMovie: RemoveFavoriteMovie,
    val getFavoriteMovie: GetFavoriteMovie,
    val getFavoriteMovies: GetFavoriteMovies,
)
