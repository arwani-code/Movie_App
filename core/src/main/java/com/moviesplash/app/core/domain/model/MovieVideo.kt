package com.moviesplash.app.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieVideo(
    var key: String,
    var name: String,
    var videoFavorite: Boolean
): Parcelable