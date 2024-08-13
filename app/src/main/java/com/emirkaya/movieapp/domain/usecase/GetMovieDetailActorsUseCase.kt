package com.emirkaya.movieapp.domain.usecase

import com.emirkaya.movieapp.data.model.moivedetailactors.MovieDetailActorsResponse
import com.emirkaya.movieapp.data.model.similarmovies.SimilarMovieResponse
import com.emirkaya.movieapp.domain.repository.MovieDetailRepository
import javax.inject.Inject

class GetMovieDetailActorsUseCase @Inject constructor(private val movieDetailRepository: MovieDetailRepository) {
    suspend fun execute(movieId: Int): MovieDetailActorsResponse {
        return movieDetailRepository.getMovieDetailActors(movieId)
    }
}