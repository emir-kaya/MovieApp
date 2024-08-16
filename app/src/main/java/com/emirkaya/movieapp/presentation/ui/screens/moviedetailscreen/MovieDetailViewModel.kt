package com.emirkaya.movieapp.presentation.ui.screens.moviedetailscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emirkaya.movieapp.data.model.moivedetailactors.CastActor
import com.emirkaya.movieapp.data.model.moviedetailmodel.MovieDetailResponse
import com.emirkaya.movieapp.data.model.similarmovies.SimilarMovie
import com.emirkaya.movieapp.domain.model.FavoriteMovie
import com.emirkaya.movieapp.domain.usecase.GetMovieDetailActorsUseCase
import com.emirkaya.movieapp.domain.usecase.GetMovieDetailUseCase
import com.emirkaya.movieapp.domain.usecase.GetMovieImagesUseCase
import com.emirkaya.movieapp.domain.usecase.GetSimilarMoviesUseCase
import com.emirkaya.movieapp.domain.usecase.GetTeaserVideoKeyUseCase
import com.emirkaya.movieapp.domain.usecase.favoriteusecases.FavoriteMovieUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
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
    val isExpanded: Boolean = false,
    val isFavorite: Boolean = false,
    val similarMovies: List<SimilarMovie>? = null,
    val movieDetailActors: List<CastActor>? = null
)

sealed class MovieDetailUiEvent {
    data object ToggleOverviewExpansion : MovieDetailUiEvent()
    data class AddFavorite(val movieId: Int) : MovieDetailUiEvent()
    data class RemoveFavorite(val movieId: Int) : MovieDetailUiEvent()
}

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val getTeaserVideoKeyUseCase: GetTeaserVideoKeyUseCase,
    private val getMovieImagesUseCase: GetMovieImagesUseCase,
    private val getSimilarMoviesUseCase: GetSimilarMoviesUseCase,
    private val getMovieDetailActorsUseCase: GetMovieDetailActorsUseCase,
    private val favoriteMovieUseCases: FavoriteMovieUseCases
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
                val similarMovies = getSimilarMoviesUseCase.execute(movieId).results?.mapNotNull { it }
                val movieDetailActors = getMovieDetailActorsUseCase.execute(movieId).castActor?.mapNotNull { it }

                val backdropPaths = images.backdrops?.mapNotNull { it?.filePath }

                val voteAverage = movieDetail.voteAverage ?: 0.0
                val fullStars = voteAverage.toInt() / 2
                val halfStars = if (voteAverage % 2 >= 1) 1 else 0
                val emptyStars = 5 - fullStars - halfStars

                val favoriteMovie = favoriteMovieUseCases.getFavoriteMovie(movieId)
                val isFavorite = favoriteMovie != null

                _uiState.value = MovieDetailUiState(
                    movieDetail = movieDetail,
                    teaserVideoKey = teaserVideoKey,
                    backdrops = backdropPaths,
                    fullStars = fullStars,
                    halfStars = halfStars,
                    emptyStars = emptyStars,
                    productionCompany = movieDetail.productionCompanies?.firstOrNull()?.name,
                    language = movieDetail.spokenLanguages?.firstOrNull()?.englishName,
                    similarMovies = similarMovies,
                    movieDetailActors = movieDetailActors,
                    isFavorite = isFavorite
                )
            } catch (e: Exception) {
                _uiState.value = MovieDetailUiState(error = e.message)
            }
        }
    }

    fun toggleOverviewExpansion() {
        _uiState.value = _uiState.value.copy(
            isExpanded = !_uiState.value.isExpanded
        )
    }

    fun handleEvent(event: MovieDetailUiEvent) {
        when (event) {
            is MovieDetailUiEvent.ToggleOverviewExpansion -> {
                _uiState.value = _uiState.value.copy(
                    isExpanded = !_uiState.value.isExpanded
                )
            }
            is MovieDetailUiEvent.AddFavorite -> {
                addFavorite(event.movieId)
            }
            is MovieDetailUiEvent.RemoveFavorite -> {
                removeFavorite(event.movieId)
            }
        }
    }


    fun addFavorite(movieId: Int) {
        viewModelScope.launch {
            val movieDetail = _uiState.value.movieDetail
            movieDetail?.let {
                val favoriteMovie = FavoriteMovie(
                    movieId = movieId,
                    posterPath = it.posterPath,
                    title = it.title,
                    voteAverage = it.voteAverage,
                    releaseDate = it.releaseDate
                )
                favoriteMovieUseCases.addFavoriteMovie(favoriteMovie)
                checkFavoriteStatus(movieId)
            }
        }
    }

    fun removeFavorite(movieId: Int) {
        viewModelScope.launch {
            val movieDetail = _uiState.value.movieDetail
            movieDetail?.let {
                val favoriteMovie = FavoriteMovie(
                    movieId = movieId,
                    posterPath = it.posterPath,
                    title = it.title,
                    voteAverage = it.voteAverage,
                    releaseDate = it.releaseDate
                )
                favoriteMovieUseCases.removeFavoriteMovie(favoriteMovie)
                checkFavoriteStatus(movieId)
            }
        }
    }

    private fun checkFavoriteStatus(movieId: Int) {
        viewModelScope.launch {
            val favoriteMovie = favoriteMovieUseCases.getFavoriteMovie(movieId)
            _uiState.update { it.copy(isFavorite = favoriteMovie != null) }
        }
    }
}
