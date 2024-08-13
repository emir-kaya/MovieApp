package com.emirkaya.movieapp.domain.repository

import androidx.paging.PagingData
import com.emirkaya.movieapp.data.model.moviemodel.MovieItem
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMovies(): Flow<PagingData<MovieItem>>

    fun searchMovies(query: String): Flow<PagingData<MovieItem>>
}