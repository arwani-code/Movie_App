package com.moviesplash.app.core.data.resource.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moviesplash.app.core.data.resource.local.entity.MovieHomeEntity
import com.moviesplash.app.core.data.resource.local.entity.VideoEntity
import com.moviesplash.app.core.utils.Constants
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM ${Constants.videoTableName}")
    fun getAllKeyVideo(): Flow<List<VideoEntity>>

    @Query("SELECT * FROM ${Constants.tableNameMovieHome} ORDER BY ${Constants.movieId} DESC")
    fun getListAllMovie(): Flow<List<MovieHomeEntity>>

    @Query("SELECT * FROM ${Constants.tableNameMovieHome} WHERE ${Constants.movieTitle} LIKE :title")
    fun getListSearchMovie(title: String?): Flow<List<MovieHomeEntity>>

    @Query("SELECT * FROM ${Constants.tableNameMovieHome} WHERE ${Constants.movieAction} = :movie_action")
    fun getPopularMovie(movie_action: Int): Flow<List<MovieHomeEntity>>

    @Query("SELECT * FROM ${Constants.tableNameMovieHome} WHERE ${Constants.movieAction} = :movie_action")
    fun getUpcomingMovie(movie_action: Int): Flow<List<MovieHomeEntity>>

    @Query("SELECT * FROM ${Constants.tableNameMovieHome} WHERE ${Constants.movieAction} = :movie_action ORDER BY ${Constants.movieId} DESC")
    fun getPlayingNowMovie(movie_action: Int): Flow<List<MovieHomeEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertHomeMovie(movieHome: List<MovieHomeEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovieVideo(movieVideo: List<VideoEntity>)

    @Query("UPDATE ${Constants.tableNameMovieHome} SET ${Constants.movieIsFavorite} = :state WHERE ${Constants.movieId} = :movieId")
    fun updateFavoriteMovie(state: Boolean, movieId: Int)

    @Query("SELECT * FROM ${Constants.tableNameMovieHome} WHERE ${Constants.movieIsFavorite} = 1")
    fun getFavoriteMovie(): Flow<List<MovieHomeEntity>>
}