package com.emirkaya.movieapp.data.repository

import com.emirkaya.movieapp.data.model.moviecredit.MovieCreditResponse
import com.emirkaya.movieapp.data.network.ApiService
import com.emirkaya.movieapp.domain.repository.ActorMovieCreditsRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ActorMovieCreditsRepositoryImpl@Inject constructor(private val apiService: ApiService) :
    ActorMovieCreditsRepository {
    override suspend fun getActorMovieCredits(actorId: Int): MovieCreditResponse {
        val response = apiService.getActorMovieCredits(actorId)
        if (response.isSuccessful) {
            return response.body() ?: throw IOException("Actor movie credits not found")
        } else {
            throw HttpException(response)
        }
    }
}