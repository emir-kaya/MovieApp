package com.emirkaya.movieapp.domain.usecase

import androidx.paging.PagingData
import com.emirkaya.movieapp.data.model.moviemodel.MovieItem
import com.emirkaya.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    fun execute(query: String): Flow<PagingData<MovieItem>> {
        return movieRepository.searchMovies(query)
    }
}