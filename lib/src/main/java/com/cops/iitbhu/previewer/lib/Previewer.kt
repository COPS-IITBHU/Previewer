package com.cops.iitbhu.previewer.lib

import android.app.Application
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import java.net.MalformedURLException

object Previewer {

    private lateinit var appContext: Application

    fun init(appContext: Application) {
        this.appContext = appContext
    }

    //Extracts video ID from Youtube video URL
    @Throws(MalformedURLException::class)
    fun extractYoutubeId(url: String): String {
        val param1: List<String> = url.split("&")
        val indexOfEqualsTo = param1[0].indexOf('=', 0)

        val videoId = if (indexOfEqualsTo >= 0 && indexOfEqualsTo < param1[0].length) {
            param1[0].substring(indexOfEqualsTo + 1, param1[0].length)
        } else
            param1[0].replace("https://youtu.be/", "")
        return videoId
    }

    //Creates thumbnail from Youtube video URL
    fun thumbnailFromYoutube(imageview: ImageView, url: String) {

        val videoId = extractYoutubeId(url)
        Log.d("VideoId is->", "" + videoId)
        val imageURL = "https://img.youtube.com/vi/$videoId/0.jpg"

        Glide.with(appContext).load(imageURL).into(imageview)
    }
}