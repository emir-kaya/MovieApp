package com.emirkaya.movieapp.domain.usecase

import com.emirkaya.movieapp.data.model.moviedetailmodel.MovieDetailResponse
import com.emirkaya.movieapp.domain.repository.MovieDetailRepository
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(private val movieDetailRepository: MovieDetailRepository) {
    suspend fun execute(movieId: Int, token: String): MovieDetailResponse {
        return movieDetailRepository.getMovieDetail(movieId, token)
    }
}