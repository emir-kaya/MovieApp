package com.emirkaya.movieapp.domain.repository

import com.emirkaya.movieapp.data.model.actordetail.ActorDetailResponse


interface ActorDetailRepository {
    suspend fun getActorDetail(actorId: Int): ActorDetailResponse
}