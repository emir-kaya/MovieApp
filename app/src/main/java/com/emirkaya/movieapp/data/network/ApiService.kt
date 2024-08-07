package com.emirkaya.movieapp.data.network

import com.emirkaya.movieapp.data.model.MovieDetailResponse
import com.emirkaya.movieapp.data.model.MovieResponse
import com.emirkaya.movieapp.data.model.VideoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
   @GET("popular")
   suspend fun getMovieList(
      @Header("Authorization") token: String,
      @Query("page") page: Int
   ): Response<MovieResponse>

   @GET("{movieId}")
   suspend fun getMovieDetail(
      @Path("movieId") movieId: Int,
      @Header("Authorization") token: String
   ): Response<MovieDetailResponse>

   @GET("{movieId}/videos")
   suspend fun getMovieVideos(
      @Path("movieId") movieId: Int,
      @Header("Authorization") token: String
   ): Response<VideoResponse>
}