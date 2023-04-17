package com.example.movieapps.di

import com.example.jamsostekapps.domain.usecase.NewsInteractor
import com.example.jamsostekapps.domain.usecase.NewsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun provideMovieUseCase(newsInteractor: NewsInteractor): NewsUseCase

}