package com.cops.iitbhu.previewer.lib

import android.app.Application

object Previewer {

    private lateinit var appContext: Application

    fun init(appContext: Application) {
        this.appContext = appContext
    }

}