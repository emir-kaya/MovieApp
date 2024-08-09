package com.emirkaya.movieapp.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType

import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.emirkaya.movieapp.util.Constants
import com.emirkaya.movieapp.presentation.ui.screens.moviesscreen.MoviesScreen
import com.emirkaya.movieapp.presentation.ui.screens.actorsscreen.ActorsScreen
import com.emirkaya.movieapp.presentation.ui.screens.moviedetailscreen.MovieDetailScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavGraph(navController: NavHostController = rememberAnimatedNavController(), modifier: Modifier = Modifier, setBottomBarVisible: (Boolean) -> Unit) {
    AnimatedNavHost(navController = navController, startDestination = Constants.MOVIES, modifier = modifier) {
        composable(
            route = Constants.MOVIES,
            enterTransition = { slideInHorizontally(initialOffsetX = { -1000 }) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { 1000 }) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }) }
        ) {
            setBottomBarVisible(true)
            MoviesScreen(navController)
        }
        composable(
            route = Constants.ACTORS,
            enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }) }
        ) {
            setBottomBarVisible(true)
            ActorsScreen(navController)
        }
        composable(
            route = "${Constants.MOVIE_DETAIL}/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType }),
            enterTransition = { fadeIn() + scaleIn() },
            exitTransition = { fadeOut() + scaleOut() }
        ) { backStackEntry ->
            setBottomBarVisible(false)
            val movieId = backStackEntry.arguments?.getInt("movieId") ?: 0
            MovieDetailScreen(navController, movieId)
        }
    }
}

