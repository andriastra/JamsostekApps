package com.example.jamsostekapps.ui.news

import androidx.lifecycle.*
import com.example.jamsostekapps.data.remote.Article
import com.example.jamsostekapps.data.remote.NewsResp
import com.example.jamsostekapps.domain.repository.NewsRepository
import com.example.jamsostekapps.domain.usecase.NewsUseCase
import com.example.jamsostekapps.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class NewsPaperViewModel @Inject constructor(
    private val useCase: NewsUseCase
) : ViewModel() {

    private val _page = MutableLiveData<Int>().apply {
        value = 1
    }
    val page: LiveData<Int> = _page

    private val _genre = MutableLiveData<String>().apply {
        value = ""
    }

    private val _loadMore = MutableLiveData<Boolean>().apply {
        value = false
    }

    val loadMore: LiveData<Boolean> = _loadMore
    fun setLoadMore(isLoad: Boolean) {
        _loadMore.postValue(isLoad)
    }

    private val _canLoadMore = MutableLiveData<Boolean>().apply {
        value = true
    }

    val canLoadMore: LiveData<Boolean> = _canLoadMore
    fun setCanLoadMore(canLoadMore: Boolean) {
        _canLoadMore.postValue(canLoadMore)
    }

    fun getListNews(): LiveData<Resource<NewsResp>> {
        _page.value = 1
        return useCase.getNewsData(countryCode = "us", pageNumber = page.value ?: 1).asLiveData()
    }

    fun loadMoreListNews(): LiveData<Resource<NewsResp>> {
        _page.value = (page.value)?.plus(1)
        return useCase.getNewsData(countryCode = "us", pageNumber = page.value ?: 1).asLiveData()
    }
}