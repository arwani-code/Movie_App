package com.moviesplash.app.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moviesplash.app.core.domain.model.MovieHome
import com.moviesplash.app.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val movieUseCase: MovieUseCase): ViewModel() {

    fun setFavoriteMovie(movieHome: MovieHome, state: Boolean) {
            movieUseCase.setFavoriteMovie(movieHome, state)
    }

}