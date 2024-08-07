package com.emirkaya.movieapp.presentation.ui.screens.moviedetailscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emirkaya.movieapp.data.model.MovieDetailResponse
import com.emirkaya.movieapp.domain.usecase.GetMovieDetailUseCase
import com.emirkaya.movieapp.domain.usecase.GetTeaserVideoKeyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MovieDetailUiState(
    val movieDetail: MovieDetailResponse? = null,
    val teaserVideoKey: String? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val getTeaserVideoKeyUseCase: GetTeaserVideoKeyUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MovieDetailUiState())
    val uiState: StateFlow<MovieDetailUiState> get() = _uiState

    fun getMovieDetailAndTeaserKey(movieId: Int, token: String) {
        viewModelScope.launch {
            _uiState.value = MovieDetailUiState(isLoading = true)
            try {
                val movieDetail = getMovieDetailUseCase.execute(movieId, token)
                val teaserVideoKey = getTeaserVideoKeyUseCase.execute(movieId, token)
                _uiState.value = MovieDetailUiState(
                    movieDetail = movieDetail,
                    teaserVideoKey = teaserVideoKey
                )
            } catch (e: Exception) {
                _uiState.value = MovieDetailUiState(error = e.message)
            }
        }
    }
}
