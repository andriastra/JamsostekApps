package com.example.jamsostekapps.di

import com.example.jamsostekapps.data.remote.NewsApi
import com.example.jamsostekapps.utils.Constant.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
//            .addInterceptor { chain ->
//                val newRequest = chain.request().newBuilder()
//                    .addHeader("Cache-Control", "no-cache")
//                    .addHeader("Cache-Control", "no-store")
//                    .addHeader("Content-Type", "application/json")
//                    .addHeader("Content-Type", "x-www-form-urlencoded")
//                    .addHeader("Accept", "application/json")
//                    .build()
//                chain.proceed(newRequest)
//            }
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(client: OkHttpClient): NewsApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(NewsApi::class.java)
    }
}