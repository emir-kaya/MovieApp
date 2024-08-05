package com.emirkaya.movieapp.presentation.ui.screens.moviesscreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.emirkaya.movieapp.data.model.MovieItem
import com.emirkaya.movieapp.data.model.MovieResponse
import com.emirkaya.movieapp.domain.usecase.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val getPopularMoviesUseCase: GetPopularMoviesUseCase) : ViewModel() {

    fun getPopularMovies(token: String): Flow<PagingData<MovieItem>> {
        return getPopularMoviesUseCase.execute(token).cachedIn(viewModelScope)
    }
}

