package com.emirkaya.movieapp.data.repository

import com.emirkaya.movieapp.data.model.moivedetailactors.MovieDetailActorsResponse
import com.emirkaya.movieapp.data.model.movieimagemodel.ImageResponse
import com.emirkaya.movieapp.data.model.moviedetailmodel.MovieDetailResponse
import com.emirkaya.movieapp.data.model.similarmovies.SimilarMovieResponse
import com.emirkaya.movieapp.data.network.ApiService
import com.emirkaya.movieapp.domain.repository.MovieDetailRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MovieDetailRepositoryImpl @Inject constructor(private val apiService: ApiService) : MovieDetailRepository {

    override suspend fun getMovieDetail(movieId: Int): MovieDetailResponse {
        val response = apiService.getMovieDetail(movieId)
        if (response.isSuccessful) {
            return response.body() ?: throw IOException("Movie details not found")
        } else {
            throw HttpException(response)
        }
    }

    override suspend fun getTrailerVideoKey(movieId: Int): String? {
        val response = apiService.getMovieVideos(movieId)
        if (response.isSuccessful) {
            val videos = response.body()?.results ?: emptyList()
            val teaserVideo = videos.find { it?.type.equals("Trailer", ignoreCase = true) }
            return teaserVideo?.key
        } else {
            throw Exception("Error: ${response.errorBody()?.string()}")
        }
    }

    override suspend fun getFirstVideoKey(movieId: Int): String? {
        val response = apiService.getMovieVideos(movieId)
        if (response.isSuccessful) {
            val videos = response.body()?.results ?: emptyList()
            return videos.firstOrNull()?.key
        } else {
            throw Exception("Error: ${response.errorBody()?.string()}")
        }
    }

    override suspend fun getMovieImages(movieId: Int): ImageResponse {
        val response = apiService.getMovieImages(movieId)
        if (response.isSuccessful) {
            return response.body() ?: throw IOException("Images not found")
        } else {
            throw HttpException(response)
        }
    }

    override suspend fun getSimilarMovies(movieId: Int): SimilarMovieResponse {
        val response = apiService.getSimilarMovies(movieId)
        if (response.isSuccessful) {
            return response.body() ?: throw IOException("Similar movies not found")
        } else {
            throw HttpException(response)
        }
    }

    override suspend fun getMovieDetailActors(movieId: Int): MovieDetailActorsResponse {
        val response = apiService.getMovieDetailActors(movieId)
        if (response.isSuccessful) {
            return response.body() ?: throw IOException("Actors not found")
        } else {
            throw HttpException(response)
        }
    }
}