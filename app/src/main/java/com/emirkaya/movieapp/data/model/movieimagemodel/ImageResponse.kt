package com.emirkaya.movieapp.data.model.movieimagemodel


import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("backdrops")
    val backdrops: List<Backdrop?>?,
    @SerializedName("id")
    val id: Int?,
)