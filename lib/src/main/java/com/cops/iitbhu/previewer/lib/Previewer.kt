package com.cops.iitbhu.previewer.lib

import android.app.Application
import android.widget.ImageView
import android.graphics.BitmapFactory
import android.util.Log
import java.io.IOException
import java.net.URL

object Previewer {

    private lateinit var appContext: Application

    fun init(appContext: Application) {
        this.appContext = appContext
    }

    /**
     * Generates an image ID from the youtube video link
     * @param youtubeLink
     * @return Image ID
     */
    private fun youtubeLinkToImageUrl(youtubeLink: String): String {

        val regex =
            "^((?:https?:)?//)?((?:www|m)\\.)?(youtube\\.com|youtu.be|youtube-nocookie.com)(/(?:[\\w\\-]+\\?v=|feature=|watch\\?|e/|embed/|v/)?)([\\w\\-]+)(\\S+)?\$"

        return Regex(regex).matchEntire(youtubeLink)?.groupValues?.get(5)
            ?: throw IllegalArgumentException("Invalid youtube video url")          //Checks if the given youtube Link is valid, else throws exception
    }

    /**
     * Takes youtube video link, generates the thumbnail for the video and set it in the imageview passed
     * @param youtubeLink
     * @param imageView
     */
    fun setThumbnailFromYouTubeVideoUrl(youtubeLink: String, imageView: ImageView) {

        val imageId = youtubeLinkToImageUrl(youtubeLink)
        val imageUrl = "https://img.youtube.com/vi/$imageId/0.jpg"

        try {
            val url = URL(imageUrl)
            val image = BitmapFactory.decodeStream(url.openConnection().getInputStream())
            imageView.setImageBitmap(image)
        } catch (e: IOException) {
            Log.e("Error: ", e.toString())
        }

//        Glide.with(imageView.context)
//            .load(imageUrl)
//            .into(imageView)
    }
}
