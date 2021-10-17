package com.cops.iitbhu.previewer.lib

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide

class Previewer {
    companion object{
        private var instance : Previewer? = null

        fun getInstance() : Previewer {
            if(instance==null) instance = Previewer()
            return instance!!
        }
    }

    fun printHello() {
        println("Hello World")
    }
    fun getVideoThumbnail(uri: Uri, imageView: ImageView)  {
        Glide.with(imageView)
            .asBitmap()
            .load(uri)
            .into(imageView)
    }
}