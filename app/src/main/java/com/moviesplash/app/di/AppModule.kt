package com.moviesplash.app.di

import com.moviesplash.app.core.domain.usecase.MovieInteractor
import com.moviesplash.app.core.domain.usecase.MovieUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideMovieUseCase(movieInteractor: MovieInteractor): MovieUseCase

}