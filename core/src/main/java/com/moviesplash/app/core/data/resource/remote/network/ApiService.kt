package com.moviesplash.app.core.data.resource.remote.network

import com.moviesplash.app.core.data.resource.remote.response.ListHomeResponse
import com.moviesplash.app.core.data.resource.remote.response.ListVideoResponse
import com.moviesplash.app.core.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(Constants.MOVIE_GET_POPULAR)
    suspend fun getMoviePopular(
        @Query(Constants.API_KEY_PARAM) apiKey: String,
        @Query(Constants.KEY_LANGUAGE) language: String,
        @Query(Constants.KEY_PAGE) page: Int
    ): ListHomeResponse

    @GET(Constants.MOVIE_GET_UPCOMING)
    suspend fun getMovieUpcoming(
        @Query(Constants.API_KEY_PARAM) apiKey: String,
        @Query(Constants.KEY_LANGUAGE) language: String,
        @Query(Constants.KEY_PAGE) page: Int
    ): ListHomeResponse

    @GET(Constants.MOVIE_GET_PLAYING_NOW)
    suspend fun getMoviePlayingNow(
        @Query(Constants.API_KEY_PARAM) apiKey: String,
        @Query(Constants.KEY_LANGUAGE) language: String,
        @Query(Constants.KEY_PAGE) page: Int
    ): ListHomeResponse

    @GET(Constants.MOVIE_SEARCH_PARAM)
    suspend fun getMovieSearch(
        @Query(Constants.API_KEY_PARAM) apiKey: String,
        @Query(Constants.KEY_LANGUAGE) language: String,
        @Query(Constants.SEARCH_QUERY) movieTitle: String,
        @Query(Constants.SEARCH_INCLUDE_ADULT) adult: Boolean
    ): ListHomeResponse

    @GET(Constants.MOVIE_GET_VIDEO)
    suspend fun getMovieVideo(
        @Query(Constants.API_KEY_PARAM) apiKey: String,
        @Query(Constants.KEY_LANGUAGE) language: String,
    ): ListVideoResponse
}