package com.moviesplash.app.core.domain.usecase

import com.moviesplash.app.core.data.Resource
import com.moviesplash.app.core.domain.model.MovieHome
import com.moviesplash.app.core.domain.model.MovieVideo
import com.moviesplash.app.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val movieRepository: IMovieRepository): MovieUseCase {

    override fun getMovieVideo(): Flow<Resource<List<MovieVideo>>> {
        return movieRepository.getMovieVideo()
    }

    override fun getListAllMovie(): Flow<List<MovieHome>> {
        return movieRepository.getListAllMovie()
    }

    override fun getListMovie(title: String?): Flow<Resource<List<MovieHome>>> {
        return movieRepository.getListMovie(title)
    }

    override fun getMoviePlayingNow(): Flow<Resource<List<MovieHome>>> {
        return movieRepository.getMoviePlayingNow()
    }

    override fun getMovieUpcoming(): Flow<Resource<List<MovieHome>>> {
        return movieRepository.getMovieUpcoming()
    }

    override fun getMoviePopular(): Flow<Resource<List<MovieHome>>> {
        return movieRepository.getMoviePopular()
    }

    override fun getFavoriteMovie(): Flow<List<MovieHome>> {
        return movieRepository.getFavoriteMovie()
    }

    override fun setFavoriteMovie(movieFavorite: MovieHome, state: Boolean) {
        return movieRepository.setFavoriteMovie(movieFavorite, state)
    }
}