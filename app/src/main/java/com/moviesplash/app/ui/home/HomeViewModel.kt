package com.moviesplash.app.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.moviesplash.app.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(movieUseCase: MovieUseCase) : ViewModel() {

    val moviePopular = movieUseCase.getMoviePopular().asLiveData()
    val movieUpComing = movieUseCase.getMovieUpcoming().asLiveData()
    val moviePlayingNow = movieUseCase.getMoviePlayingNow().asLiveData()

}