package com.emirkaya.movieapp.domain.repository

import androidx.paging.PagingData
import com.emirkaya.movieapp.data.model.MovieItem
import com.emirkaya.movieapp.data.model.MovieResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface MovieRepository {
    fun getPopularMovies(token: String): Flow<PagingData<MovieItem>>
}