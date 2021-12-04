package com.cops.iitbhu.previewer.lib

import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
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
    fun youtubeLinkToImageUrl(youtubeLink: String): String {

        val regex =
            "^((?:https?:)?//)?((?:www|m)\\.)?(youtube\\.com|youtu.be|youtube-nocookie.com)(/(?:[\\w\\-]+\\?v=|feature=|watch\\?|e/|embed/|v/)?)([\\w\\-]+)(\\S+)?\$"

        return Regex(regex).matchEntire(youtubeLink)?.groupValues?.get(5)
            ?: throw IllegalArgumentException("Invalid youtube video url")          //Checks if the given youtube Link is valid, else throws exception
    }

    /**
     * Takes youtube video link, generates the thumbnail for the video and set it in the imageview passed
     * @param youtubeLink Youtube video link
     * @param imageView   ImageView to display the thumbnail
     */
    fun setThumbnailFromYouTubeVideoUrl(youtubeLink: String, imageView: ImageView) {

        val imageId = youtubeLinkToImageUrl(youtubeLink)
        val imageUrl = "https://img.youtube.com/vi/$imageId/0.jpg"

        Glide.with(imageView.context)
            .load(imageUrl)
            .into(imageView)
    }

    suspend fun getThumbnailFromVideoUrl(youtubeLink: String): Bitmap? {
        return withContext(Dispatchers.IO) {
            try {
                val imageId = youtubeLinkToImageUrl(youtubeLink)
                val imageUrl = "https://img.youtube.com/vi/$imageId/0.jpg"
                val url = URL(imageUrl)
                val bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                bitmap
            } catch (e: IOException) {
                Log.e("Error: ", e.toString())
                null
            }
        }
    }
}
