package com.emirkaya.movieapp.data.network

import com.emirkaya.movieapp.data.model.actor.PeopleResponse
import com.emirkaya.movieapp.data.model.actordetail.ActorDetailResponse
import com.emirkaya.movieapp.data.model.moivedetailactors.MovieDetailActorsResponse
import com.emirkaya.movieapp.data.model.moviecredit.MovieCreditResponse
import com.emirkaya.movieapp.data.model.moviemodel.MovieResponse
import com.emirkaya.movieapp.data.model.moviedetailmodel.MovieDetailResponse
import com.emirkaya.movieapp.data.model.movieimagemodel.ImageResponse
import com.emirkaya.movieapp.data.model.movievideomodel.VideoResponse
import com.emirkaya.movieapp.data.model.similarmovies.SimilarMovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
   @GET("movie/popular")
   suspend fun getMovieList(
      @Query("page") page: Int
   ): Response<MovieResponse>

   @GET("movie/{movieId}")
   suspend fun getMovieDetail(
      @Path("movieId") movieId: Int
   ): Response<MovieDetailResponse>

   @GET("movie/{movieId}/videos")
   suspend fun getMovieVideos(
      @Path("movieId") movieId: Int
   ): Response<VideoResponse>

   @GET("movie/{movieId}/images")
   suspend fun getMovieImages(
      @Path("movieId") movieId: Int
   ): Response<ImageResponse>

   @GET("person/popular")
   suspend fun getPopularActors(
      @Query("page") page: Int
   ): Response<PeopleResponse>

   @GET("person/{actorId}")
   suspend fun getActorDetail(
      @Path("actorId") actorId: Int
   ): Response<ActorDetailResponse>

   @GET("person/{actorId}/movie_credits")
   suspend fun getActorMovieCredits(
      @Path("actorId") actorId: Int
   ): Response<MovieCreditResponse>

   @GET("movie/{movieId}/similar")
   suspend fun getSimilarMovies(
    @Path("movieId") movieId: Int
   ): Response<SimilarMovieResponse>

   @GET("movie/{movieId}/credits")
   suspend fun getMovieDetailActors(
       @Path("movieId") movieId: Int
   ): Response<MovieDetailActorsResponse>

   @GET("search/movie")
   suspend fun searchMovies(
      @Query("query") query: String,
      @Query("page") page: Int
   ): Response<MovieResponse>

   @GET("search/person")
   suspend fun searchActors(
      @Query("query") query: String,
      @Query("page") page: Int
   ): Response<PeopleResponse>
}