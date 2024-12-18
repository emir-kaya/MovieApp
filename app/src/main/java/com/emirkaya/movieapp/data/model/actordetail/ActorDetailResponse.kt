package com.emirkaya.movieapp.data.model.actordetail


import com.google.gson.annotations.SerializedName

data class ActorDetailResponse(
    @SerializedName("biography")
    val biography: String?,
    @SerializedName("birthday")
    val birthday: String?,
    @SerializedName("deathday")
    val deathday: Any?,
    @SerializedName("gender")
    val gender: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("place_of_birth")
    val placeOfBirth: String?,
    @SerializedName("popularity")
    val popularity: Double?,
    @SerializedName("profile_path")
    val profilePath: String?
)