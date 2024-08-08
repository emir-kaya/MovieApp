package com.emirkaya.movieapp.data.model.movievideomodel


import com.google.gson.annotations.SerializedName

data class Video(
    @SerializedName("id")
    val id: String?,
    @SerializedName("key")
    val key: String?,
    @SerializedName("type")
    val type: String?
)