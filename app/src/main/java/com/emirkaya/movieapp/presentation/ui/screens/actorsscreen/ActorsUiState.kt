package com.emirkaya.movieapp.presentation.ui.screens.actorsscreen

import androidx.paging.PagingData
import com.emirkaya.movieapp.data.model.actor.ActorItem
import kotlinx.coroutines.flow.Flow

data class ActorsUiState(
    val actorsFlow: Flow<PagingData<ActorItem>>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
