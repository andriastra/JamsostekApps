package com.example.jamsostekapps.di

import com.example.jamsostekapps.data.repository.NewsRepositoryImpl
import com.example.jamsostekapps.domain.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsRepository(newsRepositoryImpl: NewsRepositoryImpl): NewsRepository

}