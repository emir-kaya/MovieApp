package com.emirkaya.movieapp.domain.repository

import com.emirkaya.movieapp.data.model.MovieDetailResponse

interface MovieDetailRepository {
    suspend fun getMovieDetail(movieId: Int, token: String): MovieDetailResponse

    suspend fun getTrailerVideoKey(movieId: Int, token: String): String?

    suspend fun getFirstVideoKey(movieId: Int, token: String): String?
}