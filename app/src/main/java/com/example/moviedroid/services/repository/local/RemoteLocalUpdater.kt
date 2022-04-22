package com.example.moviedroid.services.repository.local
import com.example.moviedroid.services.model.GenreModel
import com.example.moviedroid.services.model.MovieModel
import javax.inject.Inject

class RemoteLocalUpdater @Inject constructor(
    private val mMovieDAO: MovieDAO,
    private val mGenreDAO: GenreDAO
) {
    suspend fun movieLocalUpdate(list: List<MovieModel>){
        if(list.isEmpty()) return
        mMovieDAO.clear()
        mMovieDAO.save(list)
    }

    suspend fun genreLocalUpdate(list: List<GenreModel>){
        if(list.isEmpty()) return
        mGenreDAO.clear()
        mGenreDAO.save(list)
    }
}


