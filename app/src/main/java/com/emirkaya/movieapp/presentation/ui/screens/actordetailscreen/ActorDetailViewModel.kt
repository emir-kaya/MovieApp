package com.emirkaya.movieapp.presentation.ui.screens.actordetailscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emirkaya.movieapp.data.model.actordetail.ActorDetailResponse
import com.emirkaya.movieapp.data.model.moviecredit.Cast
import com.emirkaya.movieapp.data.model.moviemodel.MovieItem
import com.emirkaya.movieapp.domain.usecase.GetActorDetailUseCase
import com.emirkaya.movieapp.domain.usecase.GetActorMovieCreditsUseCase
import com.emirkaya.movieapp.domain.usecase.GetMovieDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ActorDetailUiState(
    val actorDetail: ActorDetailResponse? = null,
    val movieCredits: List<Cast>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class ActorDetailViewModel @Inject constructor(
    private val getActorDetailUseCase: GetActorDetailUseCase,
    private val getActorMovieCreditsUseCase: GetActorMovieCreditsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ActorDetailUiState())
    val uiState: StateFlow<ActorDetailUiState> = _uiState

    private val _isExpanded = MutableStateFlow(false)
    val isExpanded: StateFlow<Boolean> = _isExpanded

    fun getActorDetail(actorId: Int) {
        viewModelScope.launch {
            _uiState.value = ActorDetailUiState(isLoading = true)
            try {
                val actorDetail = getActorDetailUseCase.execute(actorId)
                val movieCredits = getActorMovieCreditsUseCase.execute(actorId).cast?.filterNotNull()
                _uiState.value = ActorDetailUiState(actorDetail = actorDetail, movieCredits = movieCredits)
            } catch (e: Exception) {
                _uiState.value = ActorDetailUiState(error = e.message)
            }
        }
    }

    fun toggleBiographyExpansion() {
        _isExpanded.value = !_isExpanded.value
    }

    val isBiographyLong: Boolean
        get() = (_uiState.value.actorDetail?.biography?.length ?: 0) > 100
}

