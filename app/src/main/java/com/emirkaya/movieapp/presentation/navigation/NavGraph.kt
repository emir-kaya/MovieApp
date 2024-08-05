package com.emirkaya.movieapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.emirkaya.movieapp.Constants
import com.emirkaya.movieapp.presentation.ui.screens.moviesscreen.MoviesScreen
import com.emirkaya.movieapp.presentation.ui.screens.actorsscreen.ActorsScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(navController = navController, startDestination = Constants.MOVIES, modifier = modifier) {
        composable(Constants.MOVIES) { MoviesScreen(navController = navController) }
        composable(Constants.ACTORS) { ActorsScreen(navController = navController) }
    }
}