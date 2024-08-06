package com.emirkaya.movieapp.domain.usecase

import androidx.paging.PagingData
import com.emirkaya.movieapp.data.model.MovieItem
import com.emirkaya.movieapp.data.model.MovieResponse
import com.emirkaya.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) {
    fun execute(token: String): Flow<PagingData<MovieItem>> {
        return movieRepository.getPopularMovies(token)
    }
}