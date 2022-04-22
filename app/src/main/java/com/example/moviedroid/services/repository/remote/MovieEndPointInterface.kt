package com.example.moviedroid.services.repository.remote

import com.example.moviedroid.services.model.GenreModelList
import com.example.moviedroid.services.model.MovieListModel
import com.example.moviedroid.services.model.MovieModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieEndPointInterface {

    @GET("movie/upcoming")
    fun list(): Call<MovieListModel>

    @GET("genre/movie/list")
    fun getGenres(): Call<GenreModelList>

    @GET("movie/{id}")
    fun getOne(@Path(value = "id", encoded = true) id: Int): Call<MovieModel>

}