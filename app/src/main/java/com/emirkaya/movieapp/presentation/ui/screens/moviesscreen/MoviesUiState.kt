package com.emirkaya.movieapp.presentation.ui.screens.moviesscreen

import androidx.paging.PagingData
import com.emirkaya.movieapp.data.model.moviemodel.MovieItem
import kotlinx.coroutines.flow.Flow

data class MoviesUiState(
    val moviesFlow: Flow<PagingData<MovieItem>>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
