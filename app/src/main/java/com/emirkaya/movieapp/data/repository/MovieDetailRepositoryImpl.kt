package com.emirkaya.movieapp.data.repository

import com.emirkaya.movieapp.data.model.movieimagemodel.ImageResponse
import com.emirkaya.movieapp.data.model.moviedetailmodel.MovieDetailResponse
import com.emirkaya.movieapp.data.network.ApiService
import com.emirkaya.movieapp.domain.repository.MovieDetailRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MovieDetailRepositoryImpl @Inject constructor(private val apiService: ApiService) : MovieDetailRepository {

    override suspend fun getMovieDetail(movieId: Int, token: String): MovieDetailResponse {
        val response = apiService.getMovieDetail(movieId, token)
        if (response.isSuccessful) {
            return response.body() ?: throw IOException("Movie details not found")
        } else {
            throw HttpException(response)
        }
    }

    override suspend fun getTrailerVideoKey(movieId: Int, token: String): String? {
        val response = apiService.getMovieVideos(movieId, token)
        if (response.isSuccessful) {
            val videos = response.body()?.results ?: emptyList()
            val teaserVideo = videos.find { it?.type.equals("Trailer", ignoreCase = true) }
            return teaserVideo?.key
        } else {
            throw Exception("Error: ${response.errorBody()?.string()}")
        }
    }

    override suspend fun getFirstVideoKey(movieId: Int, token: String): String? {
        val response = apiService.getMovieVideos(movieId, token)
        if (response.isSuccessful) {
            val videos = response.body()?.results ?: emptyList()
            return videos.firstOrNull()?.key
        } else {
            throw Exception("Error: ${response.errorBody()?.string()}")
        }
    }

    override suspend fun getMovieImages(movieId: Int, token: String): ImageResponse {
        val response = apiService.getMovieImages(movieId, token)
        if (response.isSuccessful) {
            return response.body() ?: throw IOException("Images not found")
        } else {
            throw HttpException(response)
        }
    }
}