package com.emirkaya.movieapp.domain.usecase.favoriteusecases

import com.emirkaya.movieapp.domain.model.FavoriteMovie
import com.emirkaya.movieapp.domain.repository.FavoriteMovieRepository

class GetFavoriteMovies(private val repository: FavoriteMovieRepository) {
    suspend operator fun invoke(): List<FavoriteMovie> {
        return repository.getAllFavorites()
    }
}