package com.example.jamsostekapps.data.repository

import com.example.jamsostekapps.data.remote.*
import com.example.jamsostekapps.domain.repository.NewsRepository
import com.example.jamsostekapps.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): NewsRepository {
    override fun getNews(countryCode: String, pageNumber: Int): Flow<Resource<NewsResp>> =
        object : NetworkOnlyResource<NewsResp>() {
            override suspend fun createCall(): Flow<ApiResponse<NewsResp>> =
                remoteDataSource.getNewsData(countryCode,pageNumber)

            override suspend fun saveCallResult(data: NewsResp) {}

        }.asFlow()

}