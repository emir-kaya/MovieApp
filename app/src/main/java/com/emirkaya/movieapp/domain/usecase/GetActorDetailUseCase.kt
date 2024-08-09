package com.emirkaya.movieapp.domain.usecase

import com.emirkaya.movieapp.data.model.actordetail.ActorDetailResponse
import com.emirkaya.movieapp.domain.repository.ActorDetailRepository
import javax.inject.Inject

class GetActorDetailUseCase @Inject constructor(private val actorDetailRepository: ActorDetailRepository) {
    suspend fun execute(actorId: Int): ActorDetailResponse {
        return actorDetailRepository.getActorDetail(actorId)
    }
}