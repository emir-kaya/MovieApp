package com.emirkaya.movieapp.presentation.ui.screens.actorsscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.emirkaya.movieapp.domain.usecase.GetPopularActorsUseCase
import com.emirkaya.movieapp.domain.usecase.SearchActorsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActorsViewModel @Inject constructor(
    private val getPopularActorsUseCase: GetPopularActorsUseCase,
    private val searchActorsUseCase: SearchActorsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ActorsUiState())
    val uiState: StateFlow<ActorsUiState> get() = _uiState
    private val _searchQuery = MutableStateFlow("")

    init {
        getPopularActors()
        setupSearch()
    }

    private fun getPopularActors() {
        _uiState.value = ActorsUiState(
            actorsFlow = getPopularActorsUseCase.execute().cachedIn(viewModelScope),
            isLoading = false
        )
    }

    fun searchActors(query: String) {
        _searchQuery.value = query
    }

    private fun setupSearch() {
        viewModelScope.launch {
            _searchQuery
                .debounce(500)
                .distinctUntilChanged()
                .collect { query ->
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
    }
}
