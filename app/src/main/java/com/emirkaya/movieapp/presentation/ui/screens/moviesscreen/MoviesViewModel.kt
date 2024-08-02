package com.emirkaya.movieapp.presentation.ui.screens.moviesscreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emirkaya.movieapp.data.model.MovieResponse
import com.emirkaya.movieapp.domain.usecase.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val getPopularMoviesUseCase: GetPopularMoviesUseCase) : ViewModel() {

    private val movieResponse: MutableLiveData<MovieResponse?> = MutableLiveData()

    fun getPopularMovies(token: String) {
        viewModelScope.launch {
            val response = getPopularMoviesUseCase.execute(token)
            if (response.isSuccessful) {
                movieResponse.postValue(response.body())
            } else {
                movieResponse.postValue(null)
            }
        }
    }
}
