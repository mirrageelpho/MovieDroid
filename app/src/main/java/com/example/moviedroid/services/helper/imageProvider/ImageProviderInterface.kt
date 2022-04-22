package com.example.moviedroid.services.helper.imageProvider

import android.content.Context
import android.widget.ImageView

interface ImageProviderInterface {
    fun setImage(context: Context, mImageView: ImageView, imageLing: String)
}