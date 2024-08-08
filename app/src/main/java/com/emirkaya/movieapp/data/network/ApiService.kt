package com.emirkaya.movieapp.data.network

import com.emirkaya.movieapp.data.model.movieimagemodel.ImageResponse
import com.emirkaya.movieapp.data.model.moviedetailmodel.MovieDetailResponse
import com.emirkaya.movieapp.data.model.moviemodel.MovieResponse
import com.emirkaya.movieapp.data.model.movievideomodel.VideoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
   @GET("popular")
   suspend fun getMovieList(
      @Query("page") page: Int
   ): Response<MovieResponse>

   @GET("{movieId}")
   suspend fun getMovieDetail(
      @Path("movieId") movieId: Int,
   ): Response<MovieDetailResponse>

   @GET("{movieId}/videos")
   suspend fun getMovieVideos(
      @Path("movieId") movieId: Int,
   ): Response<VideoResponse>

   @GET("{movieId}/images")
   suspend fun getMovieImages(
      @Path("movieId") movieId: Int,
   ): Response<ImageResponse>
}