package com.emirkaya.movieapp.data.model.moviemodel


import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val movieItems: List<MovieItem>

)