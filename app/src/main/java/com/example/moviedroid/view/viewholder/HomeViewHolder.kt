package com.example.moviedroid.view.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.moviedroid.constants.Constants
import com.example.moviedroid.databinding.RowMovieBinding
import com.example.moviedroid.services.helper.FormatHelper.Companion.formatDate
import com.example.moviedroid.services.helper.imageProvider.ImageProviderImpl.Companion.setImage
import com.example.moviedroid.services.model.MovieModel



class HomeViewHolder (
    private val binding: RowMovieBinding
    ) : RecyclerView.ViewHolder(binding.root){

    fun bindData(movie: MovieModel) {
        binding.textOriginalTitle.text = movie.originalTitle
        binding.textPopularity.text = "Popularidade: ${ movie.popularity }"
        binding.textReleaseDate.text = formatDate(movie.releaseDate)
        setImage(
            itemView.context,
            binding.imagePosterPath,
            "${Constants.IAMAGE_LINK}${movie.posterPath}"
        )
    }
}

