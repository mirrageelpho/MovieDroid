package com.example.moviedroid.services.repository


import android.app.Application
import android.util.Log
import com.example.moviedroid.services.model.GenreModel
import com.example.moviedroid.services.model.GenreModelList
import com.example.moviedroid.services.model.MovieListModel
import com.example.moviedroid.services.model.MovieModel
import com.example.moviedroid.services.repository.local.GenreDAO
import com.example.moviedroid.services.repository.local.MovieDAO
import com.example.moviedroid.services.repository.local.RemoteLocalUpdater
import com.example.moviedroid.services.repository.remote.MovieApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject


class MovieRepository @Inject constructor(
    val application: Application,
    private val apiService: MovieApiService,
    private val mRemoteLocalUpdater: RemoteLocalUpdater,
    private val mMovieDAO: MovieDAO,
    private val mGenreDAO: GenreDAO,
    private val connectionVerifier: ConectionVerifier
    ) {

    suspend fun updateLocalDB() {
        if(!connectionVerifier.isConnectionAvailable()) return

        val movieResponse : Response<MovieListModel>? = try{
             apiService.getMoviesFromRemote()
        }catch (e: Exception){  Log.d("Fail ==>", e.message.toString())
            null
        }

        val genreResponse: Response<GenreModelList>? = try {
             apiService.getGenresFromRemote()
        }catch (e: Exception) { Log.d("Fail ==>", e.message.toString())
            null
        }


        movieResponse?.body()?.let { movieListModel ->
            movieListModel.results.map {
                it.genresString = it.genreIds.contentToString()
            }
            mRemoteLocalUpdater.movieLocalUpdate(movieListModel.results)
        }

        genreResponse?.body()?.let {
            mRemoteLocalUpdater.genreLocalUpdate(it.genres)
        }

    }

    suspend fun listMovies(): List<MovieModel> = withContext(Dispatchers.IO){
        mMovieDAO.list()
    }

    suspend fun listGenres(id: Int): GenreModel = withContext(Dispatchers.IO){
        mGenreDAO.get(id)
    }

    suspend fun loadMovieProfileData(id: Int): MovieModel = withContext(Dispatchers.IO){
        mMovieDAO.load(id)
    }

}