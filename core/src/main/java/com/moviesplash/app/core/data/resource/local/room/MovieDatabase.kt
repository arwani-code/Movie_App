package com.moviesplash.app.core.data.resource.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.moviesplash.app.core.data.resource.local.entity.MovieHomeEntity
import com.moviesplash.app.core.data.resource.local.entity.VideoEntity

@Database(entities = [MovieHomeEntity::class, VideoEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase: RoomDatabase() {

    abstract fun movieDao(): MovieDao

}