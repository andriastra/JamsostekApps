package com.example.jamsostekapps.data.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import timber.log.Timber
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: NewsApi) {
    suspend fun getNewsData(
        countryCode: String,
        page: Int
    ): Flow<ApiResponse<NewsResp>> {
        return flow {
            try {
                val response =
                    apiService.getNews(countryCode = countryCode, pageNumber = page)
                emit(ApiResponse.Success(response))
            } catch (e: UnknownHostException) {
                emit(ApiResponse.Error("Connection Error"))
                Timber.e(e.toString())
            } catch (e: HttpException) {
                emit(ApiResponse.Error("Error connecting to database"))
                Timber.e(e.toString())
            } catch (e: Exception) {
                emit(ApiResponse.Error("An Error Occurred"))
                Timber.e(e.stackTraceToString())
            }
        }.flowOn(Dispatchers.IO)
    }
}