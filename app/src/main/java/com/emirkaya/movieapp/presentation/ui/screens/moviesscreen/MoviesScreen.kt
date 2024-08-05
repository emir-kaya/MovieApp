package com.emirkaya.movieapp.presentation.ui.screens.moviesscreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.emirkaya.movieapp.Constants

@Composable
fun MoviesScreen(navController: NavHostController,  viewModel: MoviesViewModel = hiltViewModel()){
    LaunchedEffect(Unit) {
        viewModel.getPopularMovies(Constants.BEARER_TOKEN)
    }
}