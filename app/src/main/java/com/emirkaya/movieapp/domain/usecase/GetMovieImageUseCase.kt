package com.emirkaya.movieapp.domain.usecase

import com.emirkaya.movieapp.data.model.movieimagemodel.ImageResponse
import com.emirkaya.movieapp.domain.repository.MovieDetailRepository
import javax.inject.Inject

class GetMovieImagesUseCase @Inject constructor(private val movieDetailRepository: MovieDetailRepository) {
    suspend fun execute(movieId: Int, token: String): ImageResponse {
        return movieDetailRepository.getMovieImages(movieId, token)
    }
}

