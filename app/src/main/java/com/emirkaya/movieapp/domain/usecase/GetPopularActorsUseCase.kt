package com.emirkaya.movieapp.domain.usecase

import androidx.paging.PagingData
import com.emirkaya.movieapp.data.model.actor.ActorItem
import com.emirkaya.movieapp.domain.repository.ActorRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularActorsUseCase @Inject constructor(private val actorRepository: ActorRepository) {
    fun execute(): Flow<PagingData<ActorItem>> {
        return actorRepository.getPopularActors()
    }
}
