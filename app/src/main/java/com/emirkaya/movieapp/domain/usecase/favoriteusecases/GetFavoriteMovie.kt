package com.emirkaya.movieapp.domain.usecase.favoriteusecases

import com.emirkaya.movieapp.domain.model.FavoriteMovie
import com.emirkaya.movieapp.domain.repository.FavoriteMovieRepository

class GetFavoriteMovie(private val repository: FavoriteMovieRepository) {
    suspend operator fun invoke(movieId: Int): FavoriteMovie? {
        return repository.getFavorite(movieId)
    }
}