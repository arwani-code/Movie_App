package com.moviesplash.app.core.domain.repository

import com.moviesplash.app.core.data.Resource
import com.moviesplash.app.core.domain.model.MovieHome
import com.moviesplash.app.core.domain.model.MovieVideo
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    fun getMovieVideo(): Flow<Resource<List<MovieVideo>>>
    fun getListAllMovie(): Flow<List<MovieHome>>
    fun getListMovie(title: String?): Flow<Resource<List<MovieHome>>>
    fun getMovieUpcoming(): Flow<Resource<List<MovieHome>>>
    fun getMoviePopular(): Flow<Resource<List<MovieHome>>>
    fun getMoviePlayingNow(): Flow<Resource<List<MovieHome>>>
    fun getFavoriteMovie(): Flow<List<MovieHome>>
    fun setFavoriteMovie(movieFavorite: MovieHome, state: Boolean)
}