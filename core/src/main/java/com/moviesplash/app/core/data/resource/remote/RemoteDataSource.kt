package com.moviesplash.app.core.data.resource.remote

import android.util.Log
import com.moviesplash.app.core.data.resource.remote.network.ApiResponse
import com.moviesplash.app.core.data.resource.remote.network.ApiService
import com.moviesplash.app.core.data.resource.remote.response.ResultsItem
import com.moviesplash.app.core.data.resource.remote.response.ResultsItemVideo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getMovieVideo(
        apiKey: String,
        language: String,
    ): Flow<ApiResponse<List<ResultsItemVideo>>> {
        return flow {
            try {
                val response = apiService.getMovieVideo(apiKey, language)
                val dataVideo = response.results
                emit(ApiResponse.Success(dataVideo))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMoviePopular(
        apiKey: String,
        language: String,
        page: Int
    ): Flow<ApiResponse<List<ResultsItem>>> {
        return flow {
            try {
                val response = apiService.getMoviePopular(apiKey, language, page)
                val dataArray = response.results
                emit(ApiResponse.Success(dataArray))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMovieUpcoming(
        apiKey: String,
        language: String,
        page: Int
    ): Flow<ApiResponse<List<ResultsItem>>> {
        return flow {
            try {
                val response = apiService.getMovieUpcoming(apiKey, language, page)
                val dataArray = response.results
                emit(ApiResponse.Success(dataArray))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMoviePlayingNow(
        apiKey: String,
        language: String,
        page: Int
    ): Flow<ApiResponse<List<ResultsItem>>> {
        return flow {
            try {
                val response = apiService.getMoviePlayingNow(apiKey, language, page)
                val dataArray = response.results
                emit(ApiResponse.Success(dataArray))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getSearchMovie(
        apiKey: String,
        language: String,
        movieTitle: String,
        adult: Boolean
    ): Flow<ApiResponse<List<ResultsItem>>> =
        flow {
            try {
                val response = apiService.getMovieSearch(apiKey, language, movieTitle, adult)
                val movieResult = response.results
                emit(ApiResponse.Success(movieResult))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
}