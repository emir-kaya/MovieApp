package com.emirkaya.movieapp.presentation.ui.screens.actordetailscreen

import com.emirkaya.movieapp.data.model.actordetail.ActorDetailResponse
import com.emirkaya.movieapp.data.model.moviecredit.Cast

data class ActorDetailUiState(
    val actorDetail: ActorDetailResponse? = null,
    val movieCredits: List<Cast>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
