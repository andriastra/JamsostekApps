package com.example.jamsostekapps.data.remote

import com.example.jamsostekapps.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

abstract class NetworkOnlyResource<ResultType> {

    private var result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        when (val apiResponse = createCall().first()) {
            is ApiResponse.Success -> {
                saveCallResult(apiResponse.data)
                emit(Resource.Success(apiResponse.data))
            }
            is ApiResponse.Empty -> {
                //emit(Resource.Success(apiResponse))
            }
            is ApiResponse.Error -> {
                onFetchFailed()
                emit(Resource.Error<ResultType>(apiResponse.errorMessage))
            }
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract suspend fun createCall(): Flow<ApiResponse<ResultType>>

    protected abstract suspend fun saveCallResult(data: ResultType)

    fun asFlow(): Flow<Resource<ResultType>> = result
}