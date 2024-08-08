package com.emirkaya.movieapp.data.model.movieimagemodel


import com.google.gson.annotations.SerializedName

data class Backdrop(
    @SerializedName("file_path")
    val filePath: String?,
)