package com.example.moviedroid.viewmodel

import androidx.lifecycle.*
import com.example.moviedroid.services.repository.MovieRepository
import com.example.moviedroid.services.model.MovieModel
import com.example.moviedroid.services.repository.ConectionVerifier
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mMovieRepository: MovieRepository
    ) : ViewModel()
{
    private val mMovieList = MutableLiveData<List<MovieModel>>()
    val movieList : LiveData<List<MovieModel>> = mMovieList

    fun list() {
        viewModelScope.launch {

            mMovieRepository.updateLocalDB()

            mMovieList.value = try {
                 mMovieRepository.listMovies()
            } catch (e: Exception) {
                arrayListOf()
            }
        }

    }
}

