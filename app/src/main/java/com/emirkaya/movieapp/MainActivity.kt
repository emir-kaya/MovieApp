package com.emirkaya.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.emirkaya.movieapp.presentation.navigation.BottomNavItem
import com.emirkaya.movieapp.presentation.navigation.BottomNavigationBar
import com.emirkaya.movieapp.presentation.navigation.NavGraph
import com.emirkaya.movieapp.presentation.ui.theme.MovieAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieAppTheme {
                val navController = rememberNavController()
                Column {
                    NavGraph(navController = navController, modifier = Modifier.weight(1f))
                    BottomNavigationBar(
                        navController = navController,
                        items = listOf(
                            BottomNavItem(Constants.MOVIES, R.drawable.ic_movies, "Movies"),
                            BottomNavItem(Constants.ACTORS, R.drawable.ic_actors, "Actors")
                        )
                    )
                }
            }
        }
    }
}


