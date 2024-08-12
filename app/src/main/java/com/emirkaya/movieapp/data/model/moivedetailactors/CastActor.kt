package com.emirkaya.movieapp.data.model.moivedetailactors


import com.google.gson.annotations.SerializedName

data class CastActor(
    @SerializedName("character")
    val character: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("known_for_department")
    val knownForDepartment: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("profile_path")
    val profilePath: String?
)