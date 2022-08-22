package com.moviesplash.app.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieHome(
    var id: Int,
    var movieId: Int,
    var title: String,
    var releaseDate: String,
    var voteAverage: Double,
    var voteCount: Int,
    var posterPath: String,
    var overview: String,
    var isFavorite: Boolean,
    var movieAction: Int
): Parcelable