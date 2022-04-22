package com.example.moviedroid.services.repository.local

import androidx.room.*
import com.example.moviedroid.services.model.MovieModel
import dagger.hilt.EntryPoint

@Dao
interface MovieDAO {
    @Insert
    suspend fun save(list: List<MovieModel>)

    @Query("SELECT * FROM movies")
    suspend fun list():List<MovieModel>

    @Query("SELECT * FROM movies WHERE id = :id")
    suspend fun load(id: Int): MovieModel

    @Update
    fun update(movies: MovieModel)

    @Query("Delete  from movies")
    suspend fun clear()
}