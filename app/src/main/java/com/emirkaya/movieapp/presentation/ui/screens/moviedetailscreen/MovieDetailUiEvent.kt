package com.emirkaya.movieapp.presentation.ui.screens.moviedetailscreen

sealed class MovieDetailUiEvent {
    data object ToggleOverviewExpansion : MovieDetailUiEvent()
    data class AddFavorite(val movieId: Int) : MovieDetailUiEvent()
    data class RemoveFavorite(val movieId: Int) : MovieDetailUiEvent()
}