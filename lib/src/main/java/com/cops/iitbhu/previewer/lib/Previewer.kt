package com.cops.iitbhu.previewer.lib

import android.app.Application
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide

object Previewer {

    private lateinit var appContext: Application

    fun init(appContext: Application) {
        this.appContext = appContext
    }

    fun getVideoThumbnail(uri: Uri, imageView: ImageView) {
        Glide.with(imageView)
            .load(uri)
            .into(imageView)
    }

}