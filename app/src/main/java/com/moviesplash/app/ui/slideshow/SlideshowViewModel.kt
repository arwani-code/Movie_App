package com.moviesplash.app.ui.slideshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.moviesplash.app.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SlideshowViewModel @Inject constructor(movieUseCase: MovieUseCase): ViewModel() {
    val movieVideo = movieUseCase.getMovieVideo().asLiveData()
}