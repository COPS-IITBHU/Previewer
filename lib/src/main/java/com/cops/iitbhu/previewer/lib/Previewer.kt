package com.cops.iitbhu.previewer.lib

import android.app.Application
import android.widget.ImageView
import com.bumptech.glide.Glide

object Previewer {

    private lateinit var appContext: Application

    fun init(appContext: Application) {
        this.appContext = appContext
    }

    //Creates thumbnail from Youtube video URL
    fun setThumbnailFromYouTubeVideoUrl(url: String, imageView: ImageView) {

        val regex =
            "^((?:https?:)?//)?((?:www|m)\\.)?(youtube\\.com|youtu.be|youtube-nocookie.com)(/(?:[\\w\\-]+\\?v=|feature=|watch\\?|e/|embed/|v/)?)([\\w\\-]+)(\\S+)?\$"

        val videoId = Regex(regex).matchEntire(url)?.groupValues?.get(5)
            ?: throw IllegalArgumentException("Invalid youtube video url")

        val imageUrl = "https://img.youtube.com/vi/$videoId/0.jpg"

        Glide.with(imageView.context)
            .load(imageUrl)
            .into(imageView)
    }
}
