package com.emirkaya.movieapp.presentation.ui.screens.favoritesscreen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.emirkaya.movieapp.R
import com.emirkaya.movieapp.domain.model.toMovieItem
import com.emirkaya.movieapp.presentation.ui.screens.favoritescreen.FavoritesViewModel
import com.emirkaya.movieapp.presentation.ui.screens.moviesscreen.MovieCard


@Composable
fun FavoritesScreen(
    onNavigateToMovieDetail: (Int) -> Unit,
    viewModel: FavoritesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.getFavoriteMovies()
    }

    Box(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        when {
            uiState.isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            uiState.error != null -> {
                Text(
                    text = uiState.error ?: "Unknown Error",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            else -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(8.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(uiState.favoriteMovies.size) { index ->
                        val movie = uiState.favoriteMovies[index]
                        MovieCard(
                            movie = movie.toMovieItem(),
                            onClick = { onNavigateToMovieDetail(movie.toMovieItem().id) }
                        )
                    }
                }
            }
        }


        FloatingActionButton(
            onClick = {
                viewModel.exportFavoritesToPdf(context) {
                    Toast.makeText(context, "PDF saved successfully", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            contentColor = MaterialTheme.colorScheme.surface,
            containerColor = MaterialTheme.colorScheme.tertiary
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_download),
                contentDescription = "Save as PDF",
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

