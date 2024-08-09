package com.emirkaya.movieapp.presentation.ui.screens.actorsscreen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.emirkaya.movieapp.R

@Composable
fun ActorsScreen(navController: NavHostController, viewModel: ActorsViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    val actorsPagingData = uiState.actorsFlow?.collectAsLazyPagingItems()

    when {
        uiState.isLoading -> {
            CircularProgressIndicator()
        }
        uiState.error != null -> {
            Text(text = uiState.error ?: stringResource(R.string.unknown_error))
        }
        actorsPagingData != null -> {
            LazyColumn(
                contentPadding = PaddingValues(8.dp)
            ) {
                items(actorsPagingData.itemCount) { index ->
                    val actor = actorsPagingData[index]
                    actor?.let {
                        ActorCard(actor = it, navController = navController)
                    }
                }
            }
        }
    }
}

