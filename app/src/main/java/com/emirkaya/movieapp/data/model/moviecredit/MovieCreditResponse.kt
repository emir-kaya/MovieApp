package com.emirkaya.movieapp.data.model.moviecredit


import com.google.gson.annotations.SerializedName

data class MovieCreditResponse(
    @SerializedName("cast")
    val cast: List<Cast?>?,
    @SerializedName("id")
    val id: Int?
)