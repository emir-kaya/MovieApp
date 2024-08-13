package com.emirkaya.movieapp.data.model.similarmovies


import com.google.gson.annotations.SerializedName

data class SimilarMovieResponse(
    @SerializedName("results")
    val results: List<SimilarMovie?>?,
)