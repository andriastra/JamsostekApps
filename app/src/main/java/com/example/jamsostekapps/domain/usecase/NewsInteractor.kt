package com.example.jamsostekapps.domain.usecase

import com.example.jamsostekapps.data.remote.NewsResp
import com.example.jamsostekapps.domain.repository.NewsRepository
import com.example.jamsostekapps.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsInteractor @Inject constructor(private val newsRepository: NewsRepository) :
    NewsUseCase {
    override fun getNewsData(countryCode: String, pageNumber: Int): Flow<Resource<NewsResp>>  =
        newsRepository.getNews(countryCode = countryCode, pageNumber = pageNumber)
}