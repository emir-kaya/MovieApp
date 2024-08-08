package com.emirkaya.movieapp.domain.repository

import com.emirkaya.movieapp.data.model.movieimagemodel.ImageResponse
import com.emirkaya.movieapp.data.model.moviedetailmodel.MovieDetailResponse

interface MovieDetailRepository {
    suspend fun getMovieDetail(movieId: Int): MovieDetailResponse

    suspend fun getTrailerVideoKey(movieId: Int): String?

    suspend fun getFirstVideoKey(movieId: Int): String?

    suspend fun getMovieImages(movieId: Int): ImageResponse
}