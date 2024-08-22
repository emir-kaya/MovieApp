package com.emirkaya.movieapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.navArgument
import com.emirkaya.movieapp.presentation.ui.screens.actordetailscreen.ActorDetailRoute
import com.emirkaya.movieapp.util.Constants
import com.emirkaya.movieapp.presentation.ui.screens.moviesscreen.MoviesScreen
import com.emirkaya.movieapp.presentation.ui.screens.actorsscreen.ActorsScreen
import com.emirkaya.movieapp.presentation.ui.screens.favoritesscreen.FavoritesScreen
import com.emirkaya.movieapp.presentation.ui.screens.moviedetailscreen.MovieDetailRoute
import androidx.navigation.compose.composable



@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    setBottomBarVisible: (Boolean) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Constants.MOVIES,
        modifier = modifier
    ) {

        composable(route = Constants.MOVIES) {
            setBottomBarVisible(true)
            MoviesScreen(onNavigateToMovieDetail = { movieId ->
                navController.navigate("${Constants.MOVIE_DETAIL}/$movieId")
            })
        }

        composable(route = Constants.FAVORITES) {
            setBottomBarVisible(true)
            FavoritesScreen(onNavigateToMovieDetail = { movieId ->
                navController.navigate("${Constants.MOVIE_DETAIL}/$movieId")
            })
        }

        composable(route = Constants.ACTORS) {
            setBottomBarVisible(true)
            ActorsScreen(onNavigateToActorDetail = { actorId ->
                navController.navigate("${Constants.ACTOR_DETAIL}/$actorId")
            })
        }

        composable(
            route = "${Constants.MOVIE_DETAIL}/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            setBottomBarVisible(false)
            val movieId = backStackEntry.arguments?.getInt("movieId") ?: 0

            MovieDetailRoute(
                movieId = movieId,
                onNavigateToActorDetail = { actorId ->
                    navController.navigate("${Constants.ACTOR_DETAIL}/$actorId")
                },
                onNavigateToSimilarMovieDetail = { similarMovieId ->
                    navController.navigate("${Constants.MOVIE_DETAIL}/$similarMovieId")
                }
            )
        }

        composable(
            route = "${Constants.ACTOR_DETAIL}/{actorId}",
            arguments = listOf(navArgument("actorId") { type = NavType.IntType })
        ) { backStackEntry ->
            setBottomBarVisible(false)
            val actorId = backStackEntry.arguments?.getInt("actorId") ?: 0
            ActorDetailRoute(
                actorId = actorId,
                onNavigateToActorMovieDetail = { movieId ->
                    navController.navigate("${Constants.MOVIE_DETAIL}/$movieId")
                }
            )
        }
    }
}


