package com.emirkaya.movieapp.di

import com.emirkaya.movieapp.data.network.ApiClient
import com.emirkaya.movieapp.data.network.ApiService
import com.emirkaya.movieapp.data.repository.MovieDetailRepositoryImpl
import com.emirkaya.movieapp.data.repository.MovieRepositoryImpl
import com.emirkaya.movieapp.domain.repository.MovieDetailRepository
import com.emirkaya.movieapp.domain.repository.MovieRepository
import com.emirkaya.movieapp.domain.usecase.GetMovieDetailUseCase
import com.emirkaya.movieapp.domain.usecase.GetPopularMoviesUseCase
import com.emirkaya.movieapp.util.Constants
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

}
