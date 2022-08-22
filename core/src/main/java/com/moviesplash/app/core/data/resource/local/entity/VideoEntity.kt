package com.moviesplash.app.core.data.resource.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.moviesplash.app.core.utils.Constants
import kotlinx.parcelize.Parcelize

@Entity(tableName = Constants.videoTableName)
@Parcelize
data class VideoEntity(
    @PrimaryKey
    @NonNull
    @field:ColumnInfo(name = Constants.MOVIE_VIDEO_KEY)
    var keyVideo: String,

    @NonNull
    @field:ColumnInfo(name = Constants.video_name)
    var name: String,

    @NonNull
    @field:ColumnInfo(name = Constants.movieIsFavorite)
    var videoFavorite: Boolean
): Parcelable