package com.emirkaya.movieapp.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState


@Composable
fun BottomNavigationBar(navController: NavHostController, items: List<BottomNavItem>) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.tertiary,
        contentColor = Color.Black
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                modifier = Modifier.height(70.dp),
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title, modifier = Modifier.size(30.dp)) },
                label = { Text(text = item.title, fontWeight = if (currentRoute == item.route) FontWeight.Bold else FontWeight.Normal) },
                selectedContentColor = MaterialTheme.colorScheme.onTertiary,
                unselectedContentColor = MaterialTheme.colorScheme.surface,
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}

data class BottomNavItem(val route: String, @DrawableRes val icon: Int, val title: String)




