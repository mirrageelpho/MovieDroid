package com.example.moviedroid.services.helper

import android.os.Build
import android.view.Window
import android.view.WindowInsets
import android.view.WindowManager

class NoActionBar {
    companion object {
        fun hideActionBar(window: Window){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {

                window.insetsController?.hide(WindowInsets.Type.statusBars())
            } else {

                window.setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
                )
            }
        }
    }
}