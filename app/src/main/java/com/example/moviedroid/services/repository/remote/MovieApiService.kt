package com.example.moviedroid.services.repository.remote


import com.example.moviedroid.services.model.GenreModelList
import com.example.moviedroid.services.model.MovieListModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class MovieApiService @Inject constructor(
    private val apiMovie: MovieEndPointInterface,
) {
    suspend fun getGenresFromRemote(): Response<GenreModelList> = withContext(Dispatchers.IO) {
        val call: Call<GenreModelList> = apiMovie.getGenres()
        call.execute()
    }

    suspend fun getMoviesFromRemote(): Response<MovieListModel> = withContext(Dispatchers.IO){
        val call: Call<MovieListModel> = apiMovie.list()
        call.execute()
    }

}