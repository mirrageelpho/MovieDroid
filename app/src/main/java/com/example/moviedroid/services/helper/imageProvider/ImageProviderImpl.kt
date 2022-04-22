package com.example.moviedroid.services.helper.imageProvider

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

class ImageProviderImpl {
    companion object : ImageProviderInterface {
        override fun setImage(context: Context, mImageView: ImageView, imageLing: String) {
            Glide.with(context)
                .load(imageLing)
                .into(mImageView)
        }
    }
}