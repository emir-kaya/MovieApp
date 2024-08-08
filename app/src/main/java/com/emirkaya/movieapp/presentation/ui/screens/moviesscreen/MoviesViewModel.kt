package com.emirkaya.movieapp.presentation.ui.screens.moviesscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.emirkaya.movieapp.util.Constants
import com.emirkaya.movieapp.data.model.moviemodel.MovieItem
import com.emirkaya.movieapp.domain.usecase.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

data class MoviesUiState(
    val moviesFlow: Flow<PagingData<MovieItem>>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class MoviesViewModel @Inject constructor(private val getPopularMoviesUseCase: GetPopularMoviesUseCase) : ViewModel() {
    private val _uiState = MutableStateFlow(MoviesUiState())
    val uiState: StateFlow<MoviesUiState> get() = _uiState

    init {
        getPopularMovies()
    }

    fun getPopularMovies() {
        _uiState.value = MoviesUiState(
            moviesFlow = getPopularMoviesUseCase.execute().cachedIn(viewModelScope),
            isLoading = false
        )
    }
}

