package com.emirkaya.movieapp.presentation.ui.screens.moviedetailscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emirkaya.movieapp.data.model.moviedetailmodel.MovieDetailResponse
import com.emirkaya.movieapp.domain.usecase.GetMovieDetailUseCase
import com.emirkaya.movieapp.domain.usecase.GetMovieImagesUseCase
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
    val error: String? = null,
    val backdrops: List<String>? = null,
    val fullStars: Int = 0,
    val halfStars: Int = 0,
    val emptyStars: Int = 0,
    val productionCompany: String? = null,
    val language: String? = null,
    val isExpanded: Boolean = false
)
sealed class MovieDetailUiEvent {
    object ToggleOverviewExpansion : MovieDetailUiEvent()
}

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val getTeaserVideoKeyUseCase: GetTeaserVideoKeyUseCase,
    private val getMovieImagesUseCase: GetMovieImagesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MovieDetailUiState())
    val uiState: StateFlow<MovieDetailUiState> get() = _uiState

    fun getMovieDetailAndTeaserKey(movieId: Int) {
        viewModelScope.launch {
            _uiState.value = MovieDetailUiState(isLoading = true)
            try {
                val movieDetail = getMovieDetailUseCase.execute(movieId)
                val teaserVideoKey = getTeaserVideoKeyUseCase.execute(movieId)
                val images = getMovieImagesUseCase.execute(movieId)


                val backdropPaths = images.backdrops?.mapNotNull { it?.filePath }

                // Yıldız
                val voteAverage = movieDetail.voteAverage ?: 0.0
                val fullStars = voteAverage.toInt() / 2
                val halfStars = if (voteAverage % 2 >= 1) 1 else 0
                val emptyStars = 5 - fullStars - halfStars

                _uiState.value = MovieDetailUiState(
                    movieDetail = movieDetail,
                    teaserVideoKey = teaserVideoKey,
                    backdrops = backdropPaths,
                    fullStars = fullStars,
                    halfStars = halfStars,
                    emptyStars = emptyStars,
                    productionCompany = movieDetail.productionCompanies?.firstOrNull()?.name,
                    language = movieDetail.spokenLanguages?.firstOrNull()?.englishName
                )
            } catch (e: Exception) {
                _uiState.value = MovieDetailUiState(error = e.message)
            }
        }
    }
    fun handleEvent(event: MovieDetailUiEvent) {
        when (event) {
            is MovieDetailUiEvent.ToggleOverviewExpansion -> {
                _uiState.value = _uiState.value.copy(
                    isExpanded = !_uiState.value.isExpanded
                )
            }
        }
    }
}

