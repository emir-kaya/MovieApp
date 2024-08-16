package com.emirkaya.movieapp.presentation.ui.screens.favoritesscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.emirkaya.movieapp.domain.model.toMovieItem
import com.emirkaya.movieapp.presentation.ui.screens.favoritescreen.FavoritesViewModel
import com.emirkaya.movieapp.presentation.ui.screens.moviesscreen.MovieCard

@Composable
fun FavoritesScreen(navController: NavHostController, viewModel: FavoritesViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getFavoriteMovies()
    }
    Column {
        when {
            uiState.isLoading -> {
                CircularProgressIndicator()
            }
            uiState.error != null -> {
                Text(text = uiState.error ?: "Unknown Error")
            }
            else -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    items(uiState.favoriteMovies.size) { index ->
                        val movie = uiState.favoriteMovies[index]
                        MovieCard(movie = movie.toMovieItem(), navController = navController)
                    }
                }
            }
        }
    }
}
