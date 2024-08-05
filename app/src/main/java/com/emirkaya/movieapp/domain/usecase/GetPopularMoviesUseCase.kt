package com.emirkaya.movieapp.domain.usecase

import com.emirkaya.movieapp.data.model.MovieResponse
import com.emirkaya.movieapp.domain.repository.MovieRepository
import retrofit2.Response
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    suspend fun execute(token: String): Response<MovieResponse> {
        return movieRepository.getPopularMovies(token)
    }
}
