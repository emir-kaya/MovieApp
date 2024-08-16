package com.emirkaya.movieapp.domain.usecase.favoriteusecases

import com.emirkaya.movieapp.domain.model.FavoriteMovie
import com.emirkaya.movieapp.domain.repository.FavoriteMovieRepository

class AddFavoriteMovie(private val repository: FavoriteMovieRepository) {
    suspend operator fun invoke(movie: FavoriteMovie) {
        repository.insertFavorite(movie)
    }
}