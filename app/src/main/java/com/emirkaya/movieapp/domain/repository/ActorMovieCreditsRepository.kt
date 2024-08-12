package com.emirkaya.movieapp.domain.repository

import com.emirkaya.movieapp.data.model.moviecredit.MovieCreditResponse

interface ActorMovieCreditsRepository {
    suspend fun getActorMovieCredits(actorId: Int): MovieCreditResponse
}