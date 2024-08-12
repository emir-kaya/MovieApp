package com.emirkaya.movieapp.domain.usecase

import com.emirkaya.movieapp.data.model.moviecredit.MovieCreditResponse
import com.emirkaya.movieapp.domain.repository.ActorMovieCreditsRepository
import javax.inject.Inject

class GetActorMovieCreditsUseCase @Inject constructor(private val actorMovieCreditsRepository: ActorMovieCreditsRepository) {
    suspend fun execute(actorId: Int): MovieCreditResponse {
        return actorMovieCreditsRepository.getActorMovieCredits(actorId)
    }
}