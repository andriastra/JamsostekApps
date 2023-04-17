package com.example.jamsostekapps.domain.repository

import com.example.jamsostekapps.data.remote.NewsResp
import com.example.jamsostekapps.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNews(countryCode: String, pageNumber: Int): Flow<Resource<NewsResp>>
}