package com.example.jamsostekapps.domain.usecase

import com.example.jamsostekapps.data.remote.NewsResp
import com.example.jamsostekapps.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface NewsUseCase {
    fun getNewsData(countryCode: String, pageNumber: Int): Flow<Resource<NewsResp>>
}