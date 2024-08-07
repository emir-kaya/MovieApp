package com.emirkaya.movieapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.emirkaya.movieapp.Constants
import com.emirkaya.movieapp.presentation.ui.screens.moviesscreen.MoviesScreen
import com.emirkaya.movieapp.presentation.ui.screens.actorsscreen.ActorsScreen
import com.emirkaya.movieapp.presentation.ui.screens.moviedetailscreen.MovieDetailScreen

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier, setBottomBarVisible: (Boolean) -> Unit) {
    NavHost(navController = navController, startDestination = Constants.MOVIES, modifier = modifier) {
        composable(Constants.MOVIES) {
            setBottomBarVisible(true)
            MoviesScreen(navController)
        }
        composable(Constants.ACTORS) {
            setBottomBarVisible(true)
            ActorsScreen(navController)
        }
        composable(
            route = "${Constants.MOVIE_DETAIL}/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            setBottomBarVisible(false)
            val movieId = backStackEntry.arguments?.getInt("movieId") ?: 0
            MovieDetailScreen(navController, movieId)
        }
    }
}

