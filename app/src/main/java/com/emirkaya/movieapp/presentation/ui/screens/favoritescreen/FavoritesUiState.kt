package com.emirkaya.movieapp.presentation.ui.screens.favoritescreen

import com.emirkaya.movieapp.domain.model.FavoriteMovie

data class FavoritesUiState(
    val favoriteMovies: List<FavoriteMovie> = emptyList(),
    val isLoading: Boolean = true,
    val error: String? = null
)
