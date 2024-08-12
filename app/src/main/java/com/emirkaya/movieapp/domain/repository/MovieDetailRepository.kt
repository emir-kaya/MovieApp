package com.emirkaya.movieapp.domain.repository

import com.emirkaya.movieapp.data.model.moivedetailactors.MovieDetailActorsResponse
import com.emirkaya.movieapp.data.model.movieimagemodel.ImageResponse
import com.emirkaya.movieapp.data.model.moviedetailmodel.MovieDetailResponse
import com.emirkaya.movieapp.data.model.similarmovies.SimilarMovieResponse

interface MovieDetailRepository {
    suspend fun getMovieDetail(movieId: Int): MovieDetailResponse

    suspend fun getTrailerVideoKey(movieId: Int): String?

    suspend fun getFirstVideoKey(movieId: Int): String?

    suspend fun getMovieImages(movieId: Int): ImageResponse

    suspend fun getSimilarMovies(movieId: Int): SimilarMovieResponse

    suspend fun getMovieDetailActors(movieId: Int): MovieDetailActorsResponse
}