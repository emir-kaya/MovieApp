package com.emirkaya.movieapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.emirkaya.movieapp.data.dao.FavoriteMovieDao
import com.emirkaya.movieapp.data.entity.FavoriteMovieEntity

@Database(entities = [FavoriteMovieEntity::class], version = 2)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun favoriteMovieDao(): FavoriteMovieDao

    companion object {
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE favorite_movies ADD COLUMN posterPath TEXT")
                database.execSQL("ALTER TABLE favorite_movies ADD COLUMN title TEXT")
                database.execSQL("ALTER TABLE favorite_movies ADD COLUMN voteAverage REAL")
                database.execSQL("ALTER TABLE favorite_movies ADD COLUMN releaseDate TEXT")
            }
        }
    }
}