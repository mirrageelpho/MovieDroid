package com.example.moviedroid.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.moviedroid.services.helper.FormatHelper.Companion.stringToArray
import com.example.moviedroid.services.helper.FormatHelper.Companion.parseGenreString
import com.example.moviedroid.services.helper.FormatHelper.Companion.formatDate
import com.example.moviedroid.services.model.MovieModel
import com.example.moviedroid.services.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileMovieViewModel @Inject constructor(
    private val mMovieRepository: MovieRepository,
    application: Application
) : AndroidViewModel(application) {

    private var mMovie = MutableLiveData<MovieModel>()
    var movie: LiveData<MovieModel> = mMovie
    private val genres: MutableSet<String> = mutableSetOf()

    private suspend fun loadGenre(id: Int) {
        try {
            val genre = mMovieRepository.listGenres(id)
            genres.add(genre.name)
        } catch (e: Exception) {
            Log.d("Fail ==>:", e.toString())
        }
    }

    fun load(id: Int) {
        viewModelScope.launch{
            try {
                val movieProfile = mMovieRepository.loadMovieProfileData(id)

                movieProfile.genresString?.let { stringToArray(it) }?.forEach {
                    loadGenre(it.toInt())
                }
                movieProfile.genresString = parseGenreString(genres.toString())

                movieProfile.releaseDate = "Lançamento: ${formatDate(movieProfile.releaseDate)}"

                mMovie.value = movieProfile

            } catch (e: Exception) {
                Log.d("Fail ==>:", e.toString())
            }
        }

    }


}
