package com.moviesplash.app.core.data

import com.moviesplash.app.core.BuildConfig
import com.moviesplash.app.core.data.resource.local.LocalDataSource
import com.moviesplash.app.core.data.resource.remote.RemoteDataSource
import com.moviesplash.app.core.data.resource.remote.network.ApiResponse
import com.moviesplash.app.core.data.resource.remote.response.ResultsItem
import com.moviesplash.app.core.data.resource.remote.response.ResultsItemVideo
import com.moviesplash.app.core.domain.model.MovieHome
import com.moviesplash.app.core.domain.model.MovieVideo
import com.moviesplash.app.core.domain.repository.IMovieRepository
import com.moviesplash.app.core.utils.AppExecutors
import com.moviesplash.app.core.utils.Constants
import com.moviesplash.app.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {

    override fun getMovieVideo(): Flow<Resource<List<MovieVideo>>> {
        return object : NetworkBoundResource<List<MovieVideo>, List<ResultsItemVideo>>() {
            override fun loadFromDB(): Flow<List<MovieVideo>> {
                return localDataSource.getAllKeyVideo().map {
                    DataMapper.mapEntitiesVideoToDomain(it)
                }
            }

            override fun shouldFetch(data: List<MovieVideo>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsItemVideo>>> {
                return remoteDataSource.getMovieVideo(
                    BuildConfig.API_KEY,
                    Constants.DEFAULT_LANGUAGE_PARAM,
                )
            }

            override suspend fun saveCallResult(data: List<ResultsItemVideo>) {
                val movieVideo = DataMapper.mapResponseVideoToEntities(data)
                localDataSource.insertMovieVideo(movieVideo)
            }
        }.asFlow()
    }

    override fun getListAllMovie(): Flow<List<MovieHome>> {
        return localDataSource.getListAllMovie().map {
            DataMapper.mapEntitiesHomeToDomain(it)
        }
    }

    override fun getListMovie(title: String?): Flow<Resource<List<MovieHome>>> {
        return object : NetworkBoundResource<List<MovieHome>, List<ResultsItem>>(){
            override fun loadFromDB(): Flow<List<MovieHome>> {
                return localDataSource.getListSearchMovie(title).map {
                    DataMapper.mapEntitiesHomeToDomain(it)
                }
            }

            override fun shouldFetch(data: List<MovieHome>?): Boolean {
                return true
            }

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsItem>>> {
                return remoteDataSource.getSearchMovie(
                    BuildConfig.API_KEY,
                    Constants.DEFAULT_LANGUAGE_PARAM,
                    title.toString(),
                    Constants.MOVIE_ADULT_PARAM
                )
            }

            override suspend fun saveCallResult(data: List<ResultsItem>) {
                val movieList = DataMapper.mapResponseSearchToEntities(data)
                localDataSource.insertHomeMovie(movieList)
            }
        }.asFlow()
    }

    override fun getMovieUpcoming(): Flow<Resource<List<MovieHome>>> =
        object : NetworkBoundResource<List<MovieHome>, List<ResultsItem>>() {
            override fun loadFromDB(): Flow<List<MovieHome>> {
                return localDataSource.getUpcomingMovie(Constants.ACTION_UPCOMING).map {
                    DataMapper.mapEntitiesHomeToDomain(it)
                }
            }

            override fun shouldFetch(data: List<MovieHome>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsItem>>> {
                return remoteDataSource.getMovieUpcoming(
                    BuildConfig.API_KEY,
                    Constants.DEFAULT_LANGUAGE_PARAM,
                    Constants.DEFAULT_PAGE_PARAM
                )
            }

            override suspend fun saveCallResult(data: List<ResultsItem>) {
                val movieList =
                    DataMapper.mapResponseToEntitiesHome(data, Constants.ACTION_UPCOMING)
                localDataSource.insertHomeMovie(movieList)
            }
        }.asFlow()

    override fun getMoviePopular(): Flow<Resource<List<MovieHome>>> =
        object : NetworkBoundResource<List<MovieHome>, List<ResultsItem>>() {
            override fun loadFromDB(): Flow<List<MovieHome>> {
                return localDataSource.getPopularMovie(Constants.ACTION_POPULAR).map {
                    DataMapper.mapEntitiesHomeToDomain(it)
                }
            }

            override fun shouldFetch(data: List<MovieHome>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsItem>>> {
                return remoteDataSource.getMoviePopular(
                    BuildConfig.API_KEY,
                    Constants.DEFAULT_LANGUAGE_PARAM,
                    Constants.DEFAULT_PAGE_PARAM
                )
            }

            override suspend fun saveCallResult(data: List<ResultsItem>) {
                val movieList = DataMapper.mapResponseToEntitiesHome(data, Constants.ACTION_POPULAR)
                localDataSource.insertHomeMovie(movieList)
            }
        }.asFlow()

    override fun getMoviePlayingNow(): Flow<Resource<List<MovieHome>>> =
        object : NetworkBoundResource<List<MovieHome>, List<ResultsItem>>() {
            override fun loadFromDB(): Flow<List<MovieHome>> {
                return localDataSource.getPlayingNowMovie(Constants.ACTION_PLAYING_NOW).map {
                    DataMapper.mapEntitiesHomeToDomain(it)
                }
            }

            override fun shouldFetch(data: List<MovieHome>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsItem>>> {
                return remoteDataSource.getMoviePlayingNow(
                    BuildConfig.API_KEY,
                    Constants.DEFAULT_LANGUAGE_PARAM,
                    Constants.DEFAULT_PAGE_PARAM
                )
            }

            override suspend fun saveCallResult(data: List<ResultsItem>) {
                val movieList =
                    DataMapper.mapResponseToEntitiesHome(data, Constants.ACTION_PLAYING_NOW)
                localDataSource.insertHomeMovie(movieList)
            }

        }.asFlow()

    override fun getFavoriteMovie(): Flow<List<MovieHome>> {
        return localDataSource.getFavoriteMovie().map {
            DataMapper.mapEntitiesHomeToDomain(it)
        }
    }

    override fun setFavoriteMovie(movieFavorite: MovieHome, state: Boolean) {
        val movieEntity = DataMapper.mapDomainToEntitiesHome(movieFavorite)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieEntity, state) }
    }
}