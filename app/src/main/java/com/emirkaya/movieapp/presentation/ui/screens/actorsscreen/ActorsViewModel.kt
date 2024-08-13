package com.emirkaya.movieapp.presentation.ui.screens.actorsscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.emirkaya.movieapp.data.model.actor.ActorItem
import com.emirkaya.movieapp.domain.usecase.GetPopularActorsUseCase
import com.emirkaya.movieapp.domain.usecase.SearchActorsUseCase
import com.emirkaya.movieapp.presentation.ui.screens.moviesscreen.MoviesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

data class ActorsUiState(
    val actorsFlow: Flow<PagingData<ActorItem>>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class ActorsViewModel @Inject constructor(
    private val getPopularActorsUseCase: GetPopularActorsUseCase,
    private val searchActorsUseCase: SearchActorsUseCase) : ViewModel() {
    private val _uiState = MutableStateFlow(ActorsUiState())
    val uiState: StateFlow<ActorsUiState> get() = _uiState

    init {
        getPopularActors()
    }

    fun getPopularActors() {
        _uiState.value = ActorsUiState(
            actorsFlow = getPopularActorsUseCase.execute().cachedIn(viewModelScope),
            isLoading = false
        )
    }
    fun searchActors(query: String) {
        if (query.isEmpty()) {
            getPopularActors()
        } else {
            _uiState.value = ActorsUiState(
                actorsFlow = searchActorsUseCase.execute(query).cachedIn(viewModelScope),
                isLoading = false
            )
        }
    }


}