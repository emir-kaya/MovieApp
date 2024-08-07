package com.emirkaya.movieapp.domain.usecase


import com.emirkaya.movieapp.domain.repository.MovieDetailRepository
import javax.inject.Inject

class GetTeaserVideoKeyUseCase @Inject constructor(private val movieDetailRepository: MovieDetailRepository) {
    suspend fun execute(movieId: Int, token: String): String? {
        return movieDetailRepository.getTrailerVideoKey(movieId, token)
    }
}
