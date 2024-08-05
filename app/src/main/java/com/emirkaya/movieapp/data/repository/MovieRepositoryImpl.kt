package com.emirkaya.movieapp.data.repository

import com.emirkaya.movieapp.data.model.MovieResponse
import com.emirkaya.movieapp.data.network.ApiService
import com.emirkaya.movieapp.domain.repository.MovieRepository
import retrofit2.Response
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val apiService: ApiService) : MovieRepository {

    override suspend fun getPopularMovies(token: String): Response<MovieResponse> {
        return apiService.getMovieList(token)
    }
}