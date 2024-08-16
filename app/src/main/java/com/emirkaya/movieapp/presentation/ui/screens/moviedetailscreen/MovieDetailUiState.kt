package com.emirkaya.movieapp.presentation.ui.screens.moviedetailscreen

import com.emirkaya.movieapp.data.model.moivedetailactors.CastActor
import com.emirkaya.movieapp.data.model.moviedetailmodel.MovieDetailResponse
import com.emirkaya.movieapp.data.model.similarmovies.SimilarMovie

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


