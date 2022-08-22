package com.moviesplash.app.core.di

import com.moviesplash.app.core.data.MovieRepository
import com.moviesplash.app.core.domain.repository.IMovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(movie: MovieRepository): IMovieRepository

}