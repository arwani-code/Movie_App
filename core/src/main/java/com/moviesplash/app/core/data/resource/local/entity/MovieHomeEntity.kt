package com.moviesplash.app.core.data.resource.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.moviesplash.app.core.utils.Constants
import kotlinx.parcelize.Parcelize

@Entity(tableName = Constants.tableNameMovieHome)
@Parcelize
data class MovieHomeEntity(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = Constants.movieId)
    var id: Int = 0,

    @NonNull
    @ColumnInfo(name = Constants.MOVIE_ID)
    var movieId: Int,

    @NonNull
    @ColumnInfo(name = Constants.movieTitle)
    var title: String,

    @NonNull
    @ColumnInfo(name = Constants.movieReleaseDate)
    var releaseDate: String? = null,

    @NonNull
    @ColumnInfo(name = Constants.voteAverage)
    var voteAverage: Double,

    @NonNull
    @ColumnInfo(name = Constants.movieVoteCount)
    var voteCount: Int,

    @NonNull
    @ColumnInfo(name = Constants.moviePosterPath)
    var posterPath: String? = null,

    @NonNull
    @ColumnInfo(name = Constants.movieOverview)
    var overview: String,

    @NonNull
    @ColumnInfo(name = Constants.movieIsFavorite)
    var isFavorite: Boolean = false,

    @NonNull
    @ColumnInfo(name = Constants.movieAction)
    var movieAction: Int
): Parcelable