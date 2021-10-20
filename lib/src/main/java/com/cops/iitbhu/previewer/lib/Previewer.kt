package com.cops.iitbhu.previewer.lib

object Previewer {

    private lateinit var appContext: Application

    fun init(appContext: Application) {
        this.appContext = appContext
      
    }
     
    fun getVideoThumbnail(uri: Uri, imageView: ImageView)  {
        Glide.with(imageView)
            .asBitmap()
            .load(uri)
            .into(imageView)
    }

}