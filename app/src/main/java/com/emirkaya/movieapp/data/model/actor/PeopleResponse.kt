package com.emirkaya.movieapp.data.model.actor


import com.google.gson.annotations.SerializedName

data class PeopleResponse(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val actorItems: List<ActorItem>?,
)