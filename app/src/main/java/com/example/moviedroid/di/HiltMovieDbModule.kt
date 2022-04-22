
package com.example.moviedroid.di

import android.content.Context
import androidx.room.Room
import com.example.moviedroid.services.repository.local.MovieDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltMovieDbModule {
    @Singleton
    @Provides
    fun providerMovieDB(@ApplicationContext context: Context): MovieDB {
        return Room.databaseBuilder(context, MovieDB::class.java, "movieDB")
            .allowMainThreadQueries()
            .build()
    }

    @Singleton
    @Provides
    fun providerMovieDao(db: MovieDB) = db.movieDao()

    @Singleton
    @Provides
    fun providerGenreDao(db: MovieDB) = db.genreDAO()

}