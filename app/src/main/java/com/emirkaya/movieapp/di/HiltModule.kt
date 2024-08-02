package com.emirkaya.movieapp.di

import com.emirkaya.movieapp.data.network.ApiClient
import com.emirkaya.movieapp.data.network.ApiService
import com.emirkaya.movieapp.data.repository.MovieRepositoryImpl
import com.emirkaya.movieapp.domain.repository.MovieRepository
import com.emirkaya.movieapp.domain.usecase.GetPopularMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return ApiClient.getClient()
    }

    @Provides
    @Singleton
    fun provideMovieRepository(apiService: ApiService): MovieRepository {
        return MovieRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideGetPopularMoviesUseCase(movieRepository: MovieRepository): GetPopularMoviesUseCase {
        return GetPopularMoviesUseCase(movieRepository)
    }
}
