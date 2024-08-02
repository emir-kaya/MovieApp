package com.emirkaya.movieapp.domain.repository

import com.emirkaya.movieapp.data.model.MovieResponse
import retrofit2.Response

interface MovieRepository {
    suspend fun getPopularMovies(token: String): Response<MovieResponse>
}
