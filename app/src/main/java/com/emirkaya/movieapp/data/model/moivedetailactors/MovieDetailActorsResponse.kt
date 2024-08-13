package com.emirkaya.movieapp.data.model.moivedetailactors


import com.google.gson.annotations.SerializedName

data class MovieDetailActorsResponse(
    @SerializedName("cast")
    val castActor: List<CastActor?>?,
    @SerializedName("id")
    val id: Int?
)