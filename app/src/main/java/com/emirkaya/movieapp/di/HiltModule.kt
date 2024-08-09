package com.emirkaya.movieapp.di

import com.emirkaya.movieapp.data.network.ApiClient
import com.emirkaya.movieapp.data.network.ApiService
import com.emirkaya.movieapp.data.repository.ActorDetailRepositoryImpl
import com.emirkaya.movieapp.data.repository.ActorRepositoryImpl
import com.emirkaya.movieapp.data.repository.MovieDetailRepositoryImpl
import com.emirkaya.movieapp.data.repository.MovieRepositoryImpl
import com.emirkaya.movieapp.domain.repository.ActorDetailRepository
import com.emirkaya.movieapp.domain.repository.ActorRepository
import com.emirkaya.movieapp.domain.repository.MovieDetailRepository
import com.emirkaya.movieapp.domain.repository.MovieRepository
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

}
