package com.emirkaya.movieapp.di

import android.content.Context
import androidx.room.Room
import com.emirkaya.movieapp.data.dao.FavoriteMovieDao
import com.emirkaya.movieapp.data.database.MovieDatabase
import com.emirkaya.movieapp.data.network.ApiClient
import com.emirkaya.movieapp.data.network.ApiService
import com.emirkaya.movieapp.data.repository.ActorDetailRepositoryImpl
import com.emirkaya.movieapp.data.repository.ActorMovieCreditsRepositoryImpl
import com.emirkaya.movieapp.data.repository.ActorRepositoryImpl
import com.emirkaya.movieapp.data.repository.FavoriteMovieRepositoryImpl
import com.emirkaya.movieapp.data.repository.MovieDetailRepositoryImpl
import com.emirkaya.movieapp.data.repository.MovieRepositoryImpl
import com.emirkaya.movieapp.domain.repository.ActorDetailRepository
import com.emirkaya.movieapp.domain.repository.ActorMovieCreditsRepository
import com.emirkaya.movieapp.domain.repository.ActorRepository
import com.emirkaya.movieapp.domain.repository.FavoriteMovieRepository
import com.emirkaya.movieapp.domain.repository.MovieDetailRepository
import com.emirkaya.movieapp.domain.repository.MovieRepository
import com.emirkaya.movieapp.domain.usecase.favoriteusecases.AddFavoriteMovie
import com.emirkaya.movieapp.domain.usecase.favoriteusecases.FavoriteMovieUseCases
import com.emirkaya.movieapp.domain.usecase.favoriteusecases.GetFavoriteMovie
import com.emirkaya.movieapp.domain.usecase.favoriteusecases.GetFavoriteMovies
import com.emirkaya.movieapp.domain.usecase.favoriteusecases.RemoveFavoriteMovie
import com.emirkaya.movieapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        val token = Constants.BEARER_TOKEN // Token'ı burada alın (örneğin, sabit bir yerden)
        return ApiClient.getClient(token)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(apiService: ApiService): MovieRepository {
        return MovieRepositoryImpl(apiService)
    }


    @Provides
    @Singleton
    fun provideMovieDetailRepository(apiService: ApiService): MovieDetailRepository {
        return MovieDetailRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideActorRepository(apiService: ApiService): ActorRepository {
        return ActorRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideActorDetailRepository(apiService: ApiService): ActorDetailRepository {
        return ActorDetailRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideActorMovieCreditsRepository(apiService: ApiService): ActorMovieCreditsRepository{
        return ActorMovieCreditsRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MovieDatabase {
        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            "movie_database"
        )
            .addMigrations(MovieDatabase.MIGRATION_1_2)
            .build()
    }

    @Provides
    fun provideFavoriteMovieDao(db: MovieDatabase): FavoriteMovieDao {
        return db.favoriteMovieDao()
    }

    @Provides
    fun provideFavoriteMovieRepository(dao: FavoriteMovieDao): FavoriteMovieRepository {
        return FavoriteMovieRepositoryImpl(dao)
    }

    @Provides
    fun provideFavoriteMovieUseCases(repository: FavoriteMovieRepository): FavoriteMovieUseCases {
        return FavoriteMovieUseCases(
            addFavoriteMovie = AddFavoriteMovie(repository),
            removeFavoriteMovie = RemoveFavoriteMovie(repository),
            getFavoriteMovie = GetFavoriteMovie(repository),
            getFavoriteMovies = GetFavoriteMovies(repository)
        )
    }
}


