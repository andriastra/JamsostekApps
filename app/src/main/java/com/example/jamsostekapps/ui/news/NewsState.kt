package com.example.jamsostekapps.ui.news

import com.example.jamsostekapps.data.remote.Article

data class NewsState(
    val newsDataList: List<Article> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)