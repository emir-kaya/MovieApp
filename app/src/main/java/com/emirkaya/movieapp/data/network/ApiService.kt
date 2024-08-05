package com.emirkaya.movieapp.data.network

import com.emirkaya.movieapp.data.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {

   @GET("popular")
   suspend fun getMovieList(@Header("Authorization") token: String): Response<MovieResponse>
}