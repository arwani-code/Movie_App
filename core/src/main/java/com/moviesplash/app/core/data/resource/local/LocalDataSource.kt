package com.moviesplash.app.core.data.resource.local

import com.moviesplash.app.core.data.resource.local.entity.MovieHomeEntity
import com.moviesplash.app.core.data.resource.local.entity.VideoEntity
import com.moviesplash.app.core.data.resource.local.room.MovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val movieDao: MovieDao) {

    fun getAllKeyVideo(): Flow<List<VideoEntity>> = movieDao.getAllKeyVideo()

    fun getListAllMovie(): Flow<List<MovieHomeEntity>> = movieDao.getListAllMovie()

    fun getListSearchMovie(title: String?): Flow<List<MovieHomeEntity>> = movieDao.getListSearchMovie(title)

    fun getPopularMovie(movie_action: Int): Flow<List<MovieHomeEntity>> =
        movieDao.getPopularMovie(movie_action)

    fun getUpcomingMovie(movie_action: Int): Flow<List<MovieHomeEntity>> =
        movieDao.getUpcomingMovie(movie_action)

    fun getPlayingNowMovie(movie_action: Int): Flow<List<MovieHomeEntity>> =
        movieDao.getPlayingNowMovie(movie_action)

    fun getFavoriteMovie(): Flow<List<MovieHomeEntity>> = movieDao.getFavoriteMovie()

    suspend fun insertHomeMovie(movieHomeEntity: List<MovieHomeEntity>) =
        movieDao.insertHomeMovie(movieHomeEntity)

    suspend fun insertMovieVideo(video: List<VideoEntity>) = movieDao.insertMovieVideo(video)

     fun setFavoriteMovie(movie: MovieHomeEntity, state: Boolean){
        movie.isFavorite = state
        movieDao.updateFavoriteMovie(state, movie.movieId)
    }
}