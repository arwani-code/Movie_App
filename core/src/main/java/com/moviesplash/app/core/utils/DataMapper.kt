package com.moviesplash.app.core.utils

import com.moviesplash.app.core.data.resource.local.entity.MovieHomeEntity
import com.moviesplash.app.core.data.resource.local.entity.VideoEntity
import com.moviesplash.app.core.data.resource.remote.response.ResultsItem
import com.moviesplash.app.core.data.resource.remote.response.ResultsItemVideo
import com.moviesplash.app.core.domain.model.MovieHome
import com.moviesplash.app.core.domain.model.MovieVideo

object DataMapper {

    fun mapResponseVideoToEntities(movies: List<ResultsItemVideo>): List<VideoEntity> {
        val movieList = ArrayList<VideoEntity>()
        movies.map {
            movieList.add(
                VideoEntity(
                    it.key,
                    it.name,
                    false
                )
            )
        }
        return movieList
    }

    fun mapResponseSearchToEntities(movies: List<ResultsItem>): List<MovieHomeEntity> {
        val movieList = ArrayList<MovieHomeEntity>()
        movies.map { movie ->
            val movieEntity = MovieHomeEntity(
                movieId = movie.id,
                title = movie.title,
                releaseDate = movie.releaseDate,
                voteAverage = movie.voteAverage,
                voteCount = movie.voteCount,
                posterPath = movie.posterPath,
                overview = movie.overview,
                movieAction = 0,
                isFavorite = false
            )
            movieList.add(movieEntity)
        }
        return movieList
    }

    fun mapResponseToEntitiesHome(
        movies: List<ResultsItem>,
        movie_action: Int
    ): List<MovieHomeEntity> {
        val movieList = ArrayList<MovieHomeEntity>()
        movies.map { movie ->
            val movieHomeEntity = MovieHomeEntity(
                movieId = movie.id,
                title = movie.title,
                releaseDate = movie.releaseDate,
                voteAverage = movie.voteAverage,
                voteCount = movie.voteCount,
                posterPath = movie.posterPath,
                overview = movie.overview,
                movieAction = movie_action,
                isFavorite = false
            )
            movieList.add(movieHomeEntity)
        }
        return movieList
    }

    fun mapEntitiesVideoToDomain(movies: List<VideoEntity>): List<MovieVideo> =
        movies.map { MovieVideo(it.keyVideo, it.name, it.videoFavorite) }

    fun mapEntitiesHomeToDomain(movie: List<MovieHomeEntity>): List<MovieHome> =
        movie.map {
            MovieHome(
                id = it.id,
                movieId = it.movieId,
                title = it.title,
                releaseDate = it.releaseDate.toString(),
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                posterPath = it.posterPath.toString(),
                overview = it.overview,
                isFavorite = it.isFavorite,
                movieAction = it.movieAction
            )
        }

    fun mapDomainToEntitiesHome(movie: MovieHome) =
        MovieHomeEntity(
            movieId = movie.id,
            title = movie.title,
            releaseDate = movie.releaseDate,
            voteAverage = movie.voteAverage,
            voteCount = movie.voteCount,
            posterPath = movie.posterPath,
            overview = movie.overview,
            movieAction = movie.movieAction,
            isFavorite = movie.isFavorite
        )
}