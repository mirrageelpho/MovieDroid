package com.example.moviedroid.services.model

import com.google.gson.annotations.SerializedName

class MovieListModel {
    @SerializedName("page")
    var page: Int = 0
    @SerializedName("total_pages")
    var totalPages: Int = 0
    @SerializedName("total_results")
    var totalResults: Int = 0
    @SerializedName("results")
    var results: ArrayList<MovieModel> = ArrayList()
}