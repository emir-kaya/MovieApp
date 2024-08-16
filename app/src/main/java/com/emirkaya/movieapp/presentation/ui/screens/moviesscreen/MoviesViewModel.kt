package com.emirkaya.movieapp.presentation.ui.screens.moviesscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.emirkaya.movieapp.util.Constants
import com.emirkaya.movieapp.data.model.moviemodel.MovieItem
import com.emirkaya.movieapp.domain.usecase.GetPopularMoviesUseCase
import com.emirkaya.movieapp.domain.usecase.SearchMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MoviesUiState())
    val uiState: StateFlow<MoviesUiState> get() = _uiState

    private val _searchQuery = MutableStateFlow("")

    init {
        getPopularMovies()
        setupSearch()
    }

    private fun getPopularMovies() {
        _uiState.value = MoviesUiState(
            moviesFlow = getPopularMoviesUseCase.execute().cachedIn(viewModelScope),
            isLoading = false
        )
    }

    fun searchMovies(query: String) {
        _searchQuery.value = query
    }

    private fun setupSearch() {
        viewModelScope.launch {
            _searchQuery
                .debounce(500)
                .distinctUntilChanged()
                .collect { query ->
                    if (query.isEmpty()) {
                        getPopularMovies()
                    } else {
                        _uiState.value = MoviesUiState(
                            moviesFlow = searchMoviesUseCase.execute(query).cachedIn(viewModelScope),
                            isLoading = false
                        )
                    }
                }
        }
    }
}