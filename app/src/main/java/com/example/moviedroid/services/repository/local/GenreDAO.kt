package com.example.moviedroid.services.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.moviedroid.services.model.GenreModel
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Dao
interface GenreDAO {
    @Query("SELECT * FROM genres")
    suspend fun list(): List<GenreModel>

    @Query("SELECT * FROM genres WHERE id = :id")
    suspend fun get(id: Int): GenreModel

    @Insert
    suspend fun save(list: List<GenreModel>)

    @Query("Delete  from genres")
    suspend fun clear()
}