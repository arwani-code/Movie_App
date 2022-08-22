package com.moviesplash.app.ui.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.moviesplash.app.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(movieUseCase: MovieUseCase): ViewModel() {

    val getListAllMovie = movieUseCase.getListAllMovie().asLiveData()
}