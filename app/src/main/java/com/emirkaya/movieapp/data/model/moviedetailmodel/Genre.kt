package com.emirkaya.movieapp.data.model.moviedetailmodel


import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("name")
    val name: String?
)