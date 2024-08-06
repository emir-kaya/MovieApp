package com.emirkaya.movieapp.presentation.ui.screens.moviesscreen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.emirkaya.movieapp.Constants
import com.emirkaya.movieapp.R
import com.emirkaya.movieapp.data.model.MovieItem
import com.emirkaya.movieapp.data.model.MovieResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun MoviesScreen(navController: NavHostController, viewModel: MoviesViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    val moviesPagingData = uiState.moviesFlow?.collectAsLazyPagingItems()

    when {
        uiState.isLoading -> {
            CircularProgressIndicator()
        }
        uiState.error != null -> {
            Text(text = uiState.error ?: stringResource(R.string.unknown_error))
        }
        moviesPagingData != null -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(moviesPagingData.itemCount) { index ->
                    val movie = moviesPagingData[index]
                    movie?.let {
                        MovieCard(movie = it)
                    }
                }
            }
        }
    }
}