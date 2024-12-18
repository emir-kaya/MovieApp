package com.emirkaya.movieapp.data.model.moviedetailmodel


import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
    @SerializedName("backdrop_path")
    val backdropPath: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany?>?,
    @SerializedName("release_date")
    val releaseDate: String?,
    @SerializedName("runtime")
    val runtime: Int?,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage?>?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
    @SerializedName("genres")
    val genres: List<Genre?>?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("id")
    val movieId: Int?
)