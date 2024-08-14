package com.emirkaya.movieapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_movies")
data class FavoriteMovieEntity(
    @PrimaryKey val movieId: Int,
    val posterPath: String?,
    val title: String?,
    val voteAverage: Double?,
    val releaseDate: String?
)