package com.moviesplash.app.core.di

import android.content.Context
import androidx.room.Room
import com.moviesplash.app.core.data.resource.local.room.MovieDao
import com.moviesplash.app.core.data.resource.local.room.MovieDatabase
import com.moviesplash.app.core.utils.DatabaseConstant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MovieDatabase {
        val passphrase: ByteArray =
            SQLiteDatabase.getBytes(DatabaseConstant.MOVIESPLASH.toCharArray())
        val factory = SupportFactory(passphrase)
        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            DatabaseConstant.DB_NAME
        )
            .fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

    @Provides
    fun provideMovieDao(database: MovieDatabase): MovieDao = database.movieDao()

}