package com.emirkaya.movieapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.emirkaya.movieapp.data.model.MovieItem
import com.emirkaya.movieapp.data.network.ApiService
import com.emirkaya.movieapp.data.paging.MoviePagingSource
import com.emirkaya.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val apiService: ApiService) : MovieRepository {
    override fun getPopularMovies(token: String): Flow<PagingData<MovieItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviePagingSource(apiService, token) }
        ).flow
    }
}