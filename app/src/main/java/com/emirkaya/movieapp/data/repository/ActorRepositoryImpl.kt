package com.emirkaya.movieapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.emirkaya.movieapp.data.model.actor.ActorItem
import com.emirkaya.movieapp.data.model.moviemodel.MovieItem
import com.emirkaya.movieapp.data.network.ApiService
import com.emirkaya.movieapp.data.paging.ActorPagingSource
import com.emirkaya.movieapp.data.paging.SearchActorsPagingSource
import com.emirkaya.movieapp.data.paging.SearchMoviesPagingSource
import com.emirkaya.movieapp.domain.repository.ActorRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ActorRepositoryImpl @Inject constructor(private val apiService: ApiService) : ActorRepository {
    override fun getPopularActors(): Flow<PagingData<ActorItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ActorPagingSource(apiService) }
        ).flow
    }
    override fun searchActors(query: String): Flow<PagingData<ActorItem>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { SearchActorsPagingSource(apiService, query) }
        ).flow
    }
}