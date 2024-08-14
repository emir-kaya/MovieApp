package com.emirkaya.movieapp.domain.repository

import com.emirkaya.movieapp.data.model.moviemodel.MovieItem
import com.emirkaya.movieapp.domain.model.FavoriteMovie

interface FavoriteMovieRepository {
    suspend fun insertFavorite(movie: FavoriteMovie)
    suspend fun deleteFavorite(movie: FavoriteMovie)
    suspend fun getFavorite(movieId: Int): FavoriteMovie?
    suspend fun getAllFavorites(): List<FavoriteMovie>
}