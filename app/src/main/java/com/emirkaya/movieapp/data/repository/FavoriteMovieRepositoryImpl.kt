package com.emirkaya.movieapp.data.repository

import com.emirkaya.movieapp.data.dao.FavoriteMovieDao
import com.emirkaya.movieapp.data.mapper.toDomain
import com.emirkaya.movieapp.data.mapper.toEntity
import com.emirkaya.movieapp.data.model.moviemodel.MovieItem
import com.emirkaya.movieapp.domain.model.FavoriteMovie
import com.emirkaya.movieapp.domain.repository.FavoriteMovieRepository
import com.emirkaya.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class FavoriteMovieRepositoryImpl @Inject constructor(
    private val favoriteMovieDao: FavoriteMovieDao
) : FavoriteMovieRepository {
    override suspend fun insertFavorite(movie: FavoriteMovie) {
        favoriteMovieDao.insertFavorite(movie.toEntity())
    }

    override suspend fun deleteFavorite(movie: FavoriteMovie) {
        favoriteMovieDao.deleteFavorite(movie.toEntity())
    }

    override suspend fun getFavorite(movieId: Int): FavoriteMovie? {
        return favoriteMovieDao.getFavorite(movieId)?.toDomain()
    }
    override suspend fun getAllFavorites(): List<FavoriteMovie> {
        return favoriteMovieDao.getAllFavorites().map { it.toDomain() }
    }
}