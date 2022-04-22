package com.example.moviedroid.services.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
class MovieModel {
    @SerializedName("id")
    @ColumnInfo(name = "id")
    @PrimaryKey
    var id: Int = 0

    @SerializedName("original_title")
    @ColumnInfo(name = "originalTitle")
    var originalTitle: String = ""

    @SerializedName("backdrop_path")
    @ColumnInfo(name = "backdropPath")
    var backdropPath: String? = ""

    @SerializedName("poster_path")
    @ColumnInfo(name = "posterPath")
    var posterPath: String? = ""

    @SerializedName("overview")
    @ColumnInfo(name = "overview")
    var overview: String = ""

    @SerializedName("popularity")
    @ColumnInfo(name = "popularity")
    var popularity: String = ""

    @SerializedName("vote_average")
    @ColumnInfo(name = "voteAverage")
    var voteAverage: String = ""

    @SerializedName("genre_ids")
    @ColumnInfo(name = "genreIds")
    @Ignore
    var genreIds: Array<Int>? = arrayOf()

    @SerializedName("genres")
    @Ignore
    var genres: List<GenreModel> = arrayListOf()

    @SerializedName("genresString")
    @ColumnInfo(name = "genresString")
    var genresString: String? = ""

    @SerializedName("release_date")
    @ColumnInfo(name = "releaseDate")
    var releaseDate: String = ""

}
