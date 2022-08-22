package com.moviesplash.app.core.domain

import com.moviesplash.app.core.DataDummy
import com.moviesplash.app.core.data.Resource
import com.moviesplash.app.core.domain.model.MovieHome
import com.moviesplash.app.core.domain.repository.IMovieRepository
import com.moviesplash.app.core.domain.usecase.MovieInteractor
import com.moviesplash.app.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class MovieUseCaseTest {

    private lateinit var movieUseCase: MovieUseCase
    @Mock private lateinit var movieRepository: IMovieRepository
    private val dummyMovie = DataDummy.generateDummyMovie()

    @Before
    fun setup(){
        movieUseCase = MovieInteractor(movieRepository)
        val flowDummyMovie = flow {
            val resourceMovie: Resource<List<MovieHome>> = Resource.Success(dummyMovie)
            emit(resourceMovie)
        }
        `when`(movieRepository.getMoviePopular()).thenReturn(flowDummyMovie)
    }

    @Test
    fun `should get data movie popular from repository`(){
        val getFavoriteMovie = movieUseCase.getFavoriteMovie()
        verify(movieRepository).getFavoriteMovie()
        Assert.assertEquals(getFavoriteMovie, movieRepository.getFavoriteMovie())
    }
}