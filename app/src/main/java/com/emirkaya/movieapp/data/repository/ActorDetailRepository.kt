package com.emirkaya.movieapp.data.repository

import com.emirkaya.movieapp.data.model.actordetail.ActorDetailResponse
import com.emirkaya.movieapp.data.network.ApiService
import com.emirkaya.movieapp.domain.repository.ActorDetailRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ActorDetailRepositoryImpl@Inject constructor(private val apiService: ApiService) : ActorDetailRepository {
    override suspend fun getActorDetail(actorId: Int): ActorDetailResponse {
        val response = apiService.getActorDetail(actorId)
        if (response.isSuccessful) {
            return response.body() ?: throw IOException("Actor details not found")
        } else {
            throw HttpException(response)
        }
    }
}