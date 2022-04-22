package com.example.moviedroid.services.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviedroid.services.model.GenreModel
import com.example.moviedroid.services.model.MovieModel

@Database(entities = [MovieModel::class, GenreModel::class], version = 1)
abstract class MovieDB: RoomDatabase() {
    abstract fun movieDao(): MovieDAO
    abstract fun genreDAO(): GenreDAO
}