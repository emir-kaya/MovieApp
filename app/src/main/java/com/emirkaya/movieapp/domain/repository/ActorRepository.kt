package com.emirkaya.movieapp.domain.repository

import androidx.paging.PagingData
import com.emirkaya.movieapp.data.model.actor.ActorItem
import kotlinx.coroutines.flow.Flow

interface ActorRepository {
    fun getPopularActors(): Flow<PagingData<ActorItem>>
}