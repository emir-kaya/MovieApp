package com.emirkaya.movieapp.presentation.ui.screens.favoritescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emirkaya.movieapp.domain.usecase.favoriteusecases.FavoriteMovieUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val favoriteMovieUseCases: FavoriteMovieUseCases
) : ViewModel() {

    private val _uiState = MutableStateFlow(FavoritesUiState())
    val uiState: StateFlow<FavoritesUiState> = _uiState



     fun getFavoriteMovies() {
        viewModelScope.launch {
            try {
                _uiState.value = FavoritesUiState(isLoading = true)
                val favorites = favoriteMovieUseCases.getFavoriteMovies()
                _uiState.value = FavoritesUiState(favoriteMovies = favorites, isLoading = false)
            } catch (e: Exception) {
                _uiState.value = FavoritesUiState(error = e.message, isLoading = false)
            }
        }
    }
}
