package com.example.moviedroid.view


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.moviedroid.constants.Constants
import com.example.moviedroid.databinding.ActivityProfileMovieBinding
import com.example.moviedroid.services.helper.NoActionBar.Companion.hideActionBar
import com.example.moviedroid.services.helper.imageProvider.ImageProviderImpl.Companion.setImage
import com.example.moviedroid.viewmodel.ProfileMovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileMovieActivity: AppCompatActivity() {
    private val mViewModel: ProfileMovieViewModel by viewModels()
    private lateinit var binding: ActivityProfileMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        hideActionBar(window)
        subscribeObserver()
        loadDataFromActivit()
    }

    override fun onResume() {
        super.onResume()
        loadDataFromActivit()
    }

    private fun subscribeObserver(){
        mViewModel.movie.observe(this)
        {
            binding.textOriginalTitle.text = it.originalTitle
            binding.textGenre.text = "Gênero: ${it.genresString}"
            binding.textVoteAverage.text = "Nota: ${it.voteAverage}"
            binding.textResume.text = it.overview
            binding.textReleaseDate.text = it.releaseDate
            setImage(this, binding.imagePosterPath,"${Constants.IAMAGE_LINK}${it.posterPath}")
            setImage(this, binding.imageBackdropPath,"${Constants.IAMAGE_LINK}${it.backdropPath}")
        }
    }

    private  fun loadDataFromActivit(){
        val bundle = intent.extras
        if (bundle != null) {
            mViewModel.load(bundle.getInt("id"))
        }

    }
}

